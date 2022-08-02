package com.liaoxin.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/14
 * @Description: 用户信息更新入参
 **/
@Data
public class UmsUpdateDTO {

    private Long id;

    private String account;

    private String password;

    private Integer role;

    private Integer status;

    private String nickName;

    private String iconUrl;

    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

}
