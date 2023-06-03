package com.liaoxin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: liaoxin
 * @Date: 2022/10/8
 **/

@Data
public class UmsRole implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String roleName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;

    private Integer version;
}

