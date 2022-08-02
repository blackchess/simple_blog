package com.liaoxin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
@Data
public class UmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(type = IdType.AUTO)
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