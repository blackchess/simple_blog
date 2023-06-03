package com.liaoxin.bpo.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liaoxin.bpo.ArticleBPO;
import com.liaoxin.client.ImageClient;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.domain.Label;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.service.ArticleService;
import com.liaoxin.service.LabelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

}
