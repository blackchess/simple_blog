package com.liaoxin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.MailSignUpDTO;
import com.liaoxin.domain.dto.UmsSignInDto;
import com.liaoxin.domain.dto.UmsUserDto;
import com.liaoxin.domain.dto.UpdatePasswordDTO;

public interface UserService extends IService<UmsUser> {

    /**
     * 用户登录功能
     * @param signInDto 登录参数类
     * @return  token字符串
     */
    String signIn(UmsSignInDto signInDto);

    /**
     * 注册功能
     */
    UmsUser signUp(UmsUserDto umsUserDto);

    /**
     * 邮件注册功能
     */
    UmsUser signUpFromMail(MailSignUpDTO mailSignUpDTO);

    /**
     * 修改用户密码
     */
    int updatePassword(UpdatePasswordDTO dto);

    /**
     * 注销登录
     */
    Boolean logout(String token);

}
