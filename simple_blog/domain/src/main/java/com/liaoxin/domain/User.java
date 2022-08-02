package com.liaoxin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
@Data
public class User implements  Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String account;

    private String password;

    private Integer role;

    private Integer status;

    private String nickName;

    private String avatarUrl;

    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

}