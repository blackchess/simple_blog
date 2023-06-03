package com.liaoxin.domain.dto;

import lombok.Data;

@Data
public class SignUpDTO {

    //手机登录必填
    private String phone;

    //邮箱登录必填
    private String mail;

    //验证码
    private String code;

}
