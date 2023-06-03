package com.liaoxin.model.dto;

import lombok.Data;

/**
 * @Auther: liaoxin
 * @Date: 2022/9/28
 **/
@Data
public class SignInDTO {
    private String account;
    private String password;
    private String code;
    private String phone;
    private String mail;
    private Integer mode;
}
