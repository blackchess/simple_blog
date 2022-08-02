package com.liaoxin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章图片类
 */
@Data
public class ArticleImage implements Serializable{

    private Long id;

    private Long articleId;

    private String imageUrl;

    private Integer articleTitleImage;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
