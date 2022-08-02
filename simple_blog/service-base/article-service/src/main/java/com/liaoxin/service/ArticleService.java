package com.liaoxin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.vo.ArticleVo;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/22
 **/
public interface ArticleService extends IService<Article> {
    /**
     * 分页查询文章
     */
    Page<ArticleVo> selectArticleList(PageDTO pageDTO);

    /**
     * 通过文章ID查询文章
     * @param id 文章ID
     */
    ArticleVo selectArticle(Long id);

    /**
     * 通过标签ID查询文章
     * @param id 标签ID
     */
    List<ArticleVo> selectArticleWithLabel(Long id);
}
