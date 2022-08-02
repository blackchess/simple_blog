package com.liaoxin.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.domain.Label;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章返回数据对象
 */
@Data
public class ArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String title;

    private String content;

    private Integer viewNum;

    private Integer commentNum;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private List<ArticleImage> imageList;

    private List<Label> labelList;
}
