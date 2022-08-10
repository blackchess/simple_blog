package com.liaoxin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.dto.SignInDTO;
import com.liaoxin.domain.dto.UmsSignInDto;
import com.liaoxin.domain.dto.SignUpDTO;
import com.liaoxin.domain.dto.UpdatePasswordDTO;

public interface UserService extends IService<UmsUser> {

    /**
     * 用户登录功能
     * @param signInDTO 登录参数类
     * @return  token字符串
     */
    String signInWithPassword(SignInDTO signInDTO);

    /**
     * 验证码登录
     * @param signInDTO
     * @return
     */
    String signInWithCode(SignInDTO signInDTO);

    /**
     * 手机注册功能
     */
    UmsUser signUpWithPhone(SignUpDTO signUpDto);

    /**
     * 邮件注册功能
     */
    UmsUser signUpWithMail(SignUpDTO signUpDTO);

    /**
     * 修改用户密码
     */
    int updatePassword(UpdatePasswordDTO dto);

    /**
     * 注销登录
     */
    Boolean logout(String token);

}
