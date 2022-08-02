package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.client.VerificationClient;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.utils.JWTUtils;
import com.liaoxin.domain.Mail;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.MailSignUpDTO;
import com.liaoxin.domain.dto.UmsSignInDto;
import com.liaoxin.domain.dto.UmsUserDto;
import com.liaoxin.domain.dto.UpdatePasswordDTO;
import com.liaoxin.mapper.UserMapper;
import com.liaoxin.service.CodeService;
import com.liaoxin.service.UserCacheService;
import com.liaoxin.service.UserService;
import com.liaoxin.common.utils.SecretUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UmsUser> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCacheService userCacheService;
    @Autowired
    CodeService codeService;
    @Autowired
    VerificationClient verificationClient;


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String signIn(UmsSignInDto signInDto) {
        String token = null;
        String password = SecretUtils.MD5Encoding(signInDto.getPassword(), WebConst.SECURT);
        UmsUser result = userMapper.selectOne(new QueryWrapper<UmsUser>().eq("account", signInDto.getAccount())
                                                                         .eq("password", password));
        if(result != null){
            token = JWTUtils.generateToken(result.getAccount());
            LOGGER.debug("SIGN-USER: " + result);
            userCacheService.setUser(result);
        }
        return token;
    }

    @Override
    public UmsUser signUp(UmsUserDto umsUserDto) {
        UmsUser userDto = new UmsUser();
        BeanUtils.copyProperties(umsUserDto,userDto);
        userDto.setCreateTime(new Date());
        userDto.setLastTime(new Date());
        //查询是否存在该账号
        QueryWrapper<UmsUser> wrapper = new QueryWrapper();
        wrapper.eq("account",userDto.getAccount()).last("limit 1");
        if(userMapper.selectOne(wrapper) != null) {
            return null;
        }
        String passwordEncode = SecretUtils.MD5Encoding(userDto.getPassword(), WebConst.SECURT);
        userDto.setPassword(passwordEncode);
        userMapper.insert(userDto);
        userDto.setPassword(null);
        return userDto;
    }

    @Override
    public UmsUser signUpFromMail(MailSignUpDTO mailSignUpDTO) {
        UmsUser umsUser = new UmsUser();
        String code = userCacheService.selectCode(mailSignUpDTO.getMail());
        if(code != null && code.equals(mailSignUpDTO.getCode())){
            if(Objects.nonNull(userMapper.selectOne(new QueryWrapper<UmsUser>().eq("account", mailSignUpDTO.getMail())))) {
                return null;
            }
            umsUser.setAccount(mailSignUpDTO.getMail());
            umsUser.setPassword(SecretUtils.MD5Encoding(code,WebConst.SECURT));
            umsUser.setCreateTime(new Date());
            umsUser.setLastTime(new Date());
            umsUser.setRole(0);
            umsUser.setStatus(1);
            userMapper.insert(umsUser);
            Mail mail = new Mail();
            mail.setSendTo(umsUser.getAccount());
            mail.setSubject("Blog注册成功");
            mail.setText("您在Blog注册的用户：" + umsUser.getAccount()+"默认密码："+ code);
            verificationClient.sendSimpleMail(mail);
            umsUser.setPassword(null);
            return umsUser;
        }
        return null;
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
                .eq("password", dto.getOldPasswordMD5()));
        if(result != null) {
            result.setPassword(dto.getNewPasswordMD5());
        }
        return userMapper.updateById(result);
    }
}
