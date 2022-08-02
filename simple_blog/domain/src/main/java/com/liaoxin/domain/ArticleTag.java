package com.liaoxin.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleTag implements Serializable {

    private Long id;

    private Long articleId;

    private Long tagId;

    private Integer status;

}
