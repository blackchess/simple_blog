package com.liaoxin.service;

import com.liaoxin.domain.vo.ArticleVo;

import java.util.List;

/**
 * 文章缓存服务类
 */
public interface ArticleCacheService {

    /**
     * 缓存文章信息
     * @param articleVo 文章数据
     */
    void setArticleCache(ArticleVo articleVo);

    /**
     * 获取缓存的文章信息
     * @param articleId 文章主键
     */
    ArticleVo selectArticleCache(Long articleId);

}
