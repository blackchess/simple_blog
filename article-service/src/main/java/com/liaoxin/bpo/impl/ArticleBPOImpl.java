package com.liaoxin.bpo.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liaoxin.bpo.ArticleBPO;
import com.liaoxin.client.ImageClient;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.domain.Label;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.service.ArticleService;
import com.liaoxin.service.LabelService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/2
 **/

@Service
@Transactional
public class ArticleBPOImpl implements ArticleBPO {

    @Autowired
    ArticleService articleService;

    @Autowired
    LabelService labelService;

    @Autowired
    ImageClient imageClient;

    @Override
    @GlobalTransactional
    public void updateArticleBPO(ArticleVo articleVo) {
        //更新文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleVo,article);
        articleService.updateById(article);
        labelService.updateBatchById(articleVo.getLabelList());
    }

    @Override
    @GlobalTransactional
    public void insertArticleBPO(ArticleVo articleVo) {
        //文章基本信息
        Article article = new Article();
        article.setTitle(articleVo.getTitle());
        article.setUserId(articleVo.getUserId());
        article.setContent(articleVo.getContent());
        article.setDiscription(articleVo.getDiscription());
        article.setCoverUrl(articleVo.getCoverUrl());
        article.setStatus(WebConst.STATUS_1);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        articleService.save(article);

        if(articleVo.getImageList() != null){
            //文章图片信息
            for(ArticleImage articleImage : articleVo.getImageList()){
                articleImage.setArticleId(article.getId());
                articleImage.setStatus(WebConst.STATUS_1);
                articleImage.setCreateTime(new Date());
                articleImage.setUpdateTime(new Date());
                imageClient.addNewArticleImage(articleImage);
            }
        }

    }

    @Override
    public Page<ArticleVo> articleVoList(PageDTO pageDTO) {
        Page<Article> articlePage = articleService.page(pageDTO);
        List<ArticleVo> articleVoList = new ArrayList<>();
        for(Article article : articlePage.getRecords()){
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            //获取文章图片信息
            ResultBean<List<ArticleImage>> imageBean = imageClient.findImageByArticle(articleVo.getId());
            articleVo.setImageList(imageBean.getData());
            //获取文章标签信息
            List<Label> labelList = labelService.list();
            articleVo.setLabelList(labelList);
            articleVoList.add(articleVo);
        }
        Page<ArticleVo> articleVoPage = new Page<>();
        BeanUtils.copyProperties(articlePage,articleVoPage);
        return articleVoPage;
    }

    @Override
    public void deleteArticle(Long userId, List<String> articleIds) {
        List<Article> articleList = articleService.listByIds(articleIds);
        for(Article article : articleList){
            if(!userId.equals(article.getUserId())){
                throw new AppException(article.getTitle() + "的拥有者不是本人");
            }
            article.setStatus(WebConst.STATUS_0);
        }
        articleService.updateBatchById(articleList);
    }

}
