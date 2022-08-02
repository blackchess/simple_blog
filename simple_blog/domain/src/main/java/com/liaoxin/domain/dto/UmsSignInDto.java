package com.liaoxin.domain.dto;

import lombok.Data;

/**
 * 登录参数
 */
@Data
public class UmsSignInDto {

    private String account;

    private String password;

    private String code;

}
