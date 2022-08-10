package com.liaoxin.domain.dto;

import lombok.Data;

/**
 * 登录参数
 */
@Data
public class SignInDTO {

    //账号
    private String phone;
    //邮箱
    private String mail;
    //密码
    private String password;
    //验证码
    private String code;
    //登录方式 1密码 2验证码
    private Integer mode;

}
