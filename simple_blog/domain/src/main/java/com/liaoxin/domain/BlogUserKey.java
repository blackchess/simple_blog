package com.liaoxin.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * blog_user
 * @author 
 */
@Data
public class BlogUserKey implements Serializable {
    private Long blogid;

    private Long userid;

    private static final long serialVersionUID = 1L;
}