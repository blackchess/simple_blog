package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.utils.JWTUtils;
import com.liaoxin.common.utils.SecretUtils;
import com.liaoxin.domain.Mail;
import com.liaoxin.domain.UmsRole;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.SignInDTO;
import com.liaoxin.domain.dto.SignUpDTO;
import com.liaoxin.domain.dto.UpdatePasswordDTO;
import com.liaoxin.domain.vo.UmsUserVo;
import com.liaoxin.mapper.UmsRoleMapper;
import com.liaoxin.mapper.UserMapper;
import com.liaoxin.service.CodeService;
import com.liaoxin.service.UmsRoleService;
import com.liaoxin.service.UserCacheService;
import com.liaoxin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/10
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UmsUser> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UmsRoleMapper umsRoleMapper;
    @Autowired
    UserCacheService userCacheService;
    @Autowired
    CodeService codeService;
    @Autowired
    UmsRoleService roleService;
//    @Autowired
//    VerificationClient verificationClient;


    @Override
    public String signInWithPassword(SignInDTO signInDTO) {
        //判断手机登录还是邮箱登录建立SQL
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(signInDTO.getPhone())){
            wrapper.eq("phone",signInDTO.getPhone());
        }
        if(StringUtils.isNotBlank(signInDTO.getMail())){
            wrapper.eq("mail",signInDTO.getMail());
        }
        wrapper.eq("password", SecretUtils.MD5Encoding(signInDTO.getPassword(), WebConst.SECURT))
                .eq("status", WebConst.STATUS_1);
        //查询用户是否存在
        UmsUser result = userMapper.selectOne(wrapper);
        if (ObjectUtils.isNull(result)){
            throw new AppException("用户名或密码错误");
        }
        //查询用户权限
        List<UmsRole> roleList = roleService.findRoleListWithUser(result.getId());
        //传入Vo返回
        UmsUserVo userVo = new UmsUserVo();
        BeanUtils.copyProperties(result,userVo);
        userVo.setUmsRoleList(roleList);
        //缓存
        userCacheService.setUser(userVo);
        return JWTUtils.generateToken(result.getAccount());
    }

    @Override
    public String signInWithCode(SignInDTO signInDTO) {
        if(StringUtils.isBlank(signInDTO.getCode())){
            throw new AppException("验证码不能为空");
        }
        String code = null;
        //判断手机登录还是邮箱登录建立SQL并获取缓存验证码
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(signInDTO.getPhone())){
            code = userCacheService.selectCode(signInDTO.getPhone());
            wrapper.eq("phone",signInDTO.getPhone());
        }
        if(StringUtils.isNotBlank(signInDTO.getMail())){
            code = userCacheService.selectCode(signInDTO.getMail());
            wrapper.eq("mail",signInDTO.getMail());
        }
        wrapper.eq("status", WebConst.STATUS_1);
        if(!signInDTO.getCode().equals(code)){
            throw new AppException("验证码错误");
        }
        //查询用户是否存在
        UmsUser result = userMapper.selectOne(wrapper);
        if (ObjectUtils.isNull(result)){
            throw new AppException("用户名或密码错误");
        }
        UmsUserVo userVo = new UmsUserVo();
        BeanUtils.copyProperties(result, userVo);

        userCacheService.setUser(userVo);
        return JWTUtils.generateToken(result.getAccount());
    }

    @Override
    public UmsUser signUpWithPhone(SignUpDTO signUpDTO) {
        if(StringUtils.isBlank(signUpDTO.getPhone())){
            throw new AppException("手机号不能为空");
        }
        //查询是否存在该账号
        QueryWrapper<UmsUser> wrapper = new QueryWrapper();
        wrapper.eq("phone", signUpDTO.getPhone());
        if(userMapper.selectOne(wrapper) != null) {
            throw new AppException("账号已存在");
        }
        //手机验证码校验
        String code = userCacheService.selectCode(signUpDTO.getPhone());
        if(StringUtils.isBlank(code)){
            throw new AppException("请先获取验证码");
        }
        if(!code.equals(signUpDTO.getCode())){
            throw new AppException("输入的验证码不正确");
        }
        //设置注册的用户信息并保存
        UmsUser user = new UmsUser();
        String passwordEncode = SecretUtils.MD5Encoding(code, WebConst.SECURT);
        user.setPassword(passwordEncode);
        user.setAccount(signUpDTO.getPhone());
        user.setPhone(signUpDTO.getPhone());
        user.setNickName("phone_"+ signUpDTO.getPhone());
        user.setCreateTime(new Date());
        user.setLastTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        //清除缓存的手机验证码
        userCacheService.deleteUser(signUpDTO.getPhone());
        user.setPassword(null);
        return user;
    }

    @Override
    public UmsUser signUpWithMail(SignUpDTO signUpDTO) {
        if (StringUtils.isBlank(signUpDTO.getMail())){
            throw new AppException("邮箱不能为空");
        }
        //验证码校验
        String code = userCacheService.selectCode(signUpDTO.getMail());
        if(StringUtils.isBlank(code)){
            throw new AppException("请先获取验证码");
        }
        if (!code.equals(signUpDTO.getCode())){
            throw new AppException("输入的验证码不正确");
        }
        //查询邮箱是否已注册
        QueryWrapper<UmsUser> wrapper = new QueryWrapper();
        wrapper.eq("mail", signUpDTO.getMail()).last("limit 1");
        UmsUser result = userMapper.selectOne(new QueryWrapper<UmsUser>().eq("mail", signUpDTO.getMail()));
        if (ObjectUtils.isNotNull(result)){
            throw new AppException("该邮箱已注册");
        }
        UmsUser newUser = new UmsUser();
        //设置注册的用户信息并保存
        newUser.setAccount(signUpDTO.getMail());
        newUser.setMail(signUpDTO.getMail());
        newUser.setNickName("mail_"+signUpDTO.getMail());
        newUser.setPassword(SecretUtils.MD5Encoding(code,WebConst.SECURT));
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());
        newUser.setLastTime(new Date());
        userMapper.insert(newUser);
        //发送邮件
        Mail mail = new Mail();
        mail.setSendTo(newUser.getMail());
        mail.setSubject("Blog注册成功");
        mail.setText("您在Blog注册的用户：" + newUser.getAccount()+"默认密码："+ code);
        //verificationClient.sendSimpleMail(mail);
        newUser.setPassword(null);
        return newUser;
    }

    @Override
    public Boolean logout(String token) {
        UmsUser user = userCacheService.selectUser(JWTUtils.deCodeStringFromToken(token));
        if(user == null) return false;
        return userCacheService.deleteUser(user.getAccount());
    }

    @Override
    public int updatePassword(UpdatePasswordDTO dto) {
        UmsUser result = userMapper.selectOne(new QueryWrapper<UmsUser>().eq("id", dto.getUserId())
                .eq("password", SecretUtils.MD5Encoding(dto.getOldPassword(), WebConst.SECURT)));
        if(result != null) {
            result.setPassword(SecretUtils.MD5Encoding(dto.getNewPassword(), WebConst.SECURT));
        }
        return userMapper.updateById(result);
    }

    @Override
    public Page<UmsUser> list(PageDTO pageDTO, UmsUser umsUser) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper(umsUser);
        return userMapper.selectPage(pageDTO,wrapper);
    }

}
