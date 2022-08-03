package com.liaoxin.bpo.impl;

import com.liaoxin.bpo.ArticleBPO;
import com.liaoxin.client.ImageClient;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.service.ArticleService;
import com.liaoxin.service.LabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/2
 **/

@Service
public class ArticleBPOImpl implements ArticleBPO {

    @Autowired
    ArticleService articleService;

    @Autowired
    LabelService labelService;

    @Autowired
    ImageClient imageClient;

    @Override
    @Transactional
    public Boolean updateArticleBPO(ArticleVo articleVo) {
        Boolean result = false;
        //更新文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleVo,article);
        articleService.updateById(article);

        result = labelService.updateBatchById(articleVo.getLabelList());

        return result;
    }

    @Override
    public Boolean insertArticleBPO(ArticleVo articleVo) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVo,article);
        article.setStatus(1);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        return articleService.save(article);
    }
}
