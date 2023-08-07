package com.liaoxin.service.impl;

import com.liaoxin.common.RedisConst;
import com.liaoxin.common.service.RedisCacheService;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.service.ArticleCacheService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleCacheServiceImpl implements ArticleCacheService {

    @Resource
    RedisCacheService redisCacheService;

    @Override
    public void setArticleCache(ArticleVo articleVo) {
        String key = RedisConst.REDIS_CMS_DATABASE + ":" + RedisConst.REDIS_CMS_PREFIX + ":" + articleVo.getId();
        redisCacheService.set(key, articleVo, RedisConst.REDIS_ARTICLE_EXPIRE);
    }

    @Override
    public ArticleVo selectArticleCache(Long articleId) {
        String key = RedisConst.REDIS_CMS_DATABASE + ":" + RedisConst.REDIS_CMS_PREFIX + ":" + articleId;
        return (ArticleVo) redisCacheService.get(key);
    }
}
