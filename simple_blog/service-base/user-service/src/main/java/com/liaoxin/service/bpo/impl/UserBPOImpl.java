package com.liaoxin.service.bpo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.SignInDTO;
import com.liaoxin.domain.dto.SignUpDTO;
import com.liaoxin.mapper.UserMapper;
import com.liaoxin.service.UserService;
import com.liaoxin.service.bpo.UserBPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/8
 **/

@Service
public class UserBPOImpl extends ServiceImpl<UserMapper,UmsUser> implements UserBPO {

    @Autowired
    UserService userService;

    @Value("${sign.mode}")
    private Integer mode;

    @Override
    public UmsUser signUp(SignUpDTO signUpDTO) {
        UmsUser result = new UmsUser();
        switch (mode){
            case 1:
                result = userService.signUpWithPhone(signUpDTO);
                break;
            case 2:
                result = userService.signUpWithMail(signUpDTO);
                break;
            default:
                throw new AppException("没有找到【"+ mode +"】的注册方式");
        }
        return result;
    }

    @Override
    public String signIn(SignInDTO signInDTO) {
        if (signInDTO.getMode() == 1){
            return userService.signInWithPassword(signInDTO);
        }
        if(signInDTO.getMode() == 2){
            return userService.signInWithCode(signInDTO);
        }
        throw new AppException("没有找到【"+ signInDTO.getMode() +"】的登录方式");
    }
}
