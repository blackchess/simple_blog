package com.liaoxin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liaoxin.domain.UmsRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/10/8
 **/

@Data
public class UmsUserVo implements Serializable {
    private Long id;

    private String account;

    private String password;

    private List<UmsRole> umsRoleList;

    private String phone;

    private String mail;

    private Integer role;

    private Integer status;

    private String nickName;

    private String iconUrl;

    private Date birthday;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;
}
