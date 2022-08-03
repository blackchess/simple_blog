package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.client.ImageClient;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.mapper.ArticleMapper;
import com.liaoxin.mapper.LabelMapper;
import com.liaoxin.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    LabelMapper labelMapper;
    @Autowired
    private ImageClient imageClient;

    @Override
    public Page<ArticleVo> selectArticleList(PageDTO pageDTO) {
        QueryWrapper<Article> wrapper = new QueryWrapper();
        wrapper.orderByDesc("create_time");
        Page<ArticleVo> articleVoPage = (Page<ArticleVo>) articleMapper.selectPage(pageDTO,wrapper);
        for(ArticleVo articleVo : articleVoPage.getRecords()){
            ResultBean<List<ArticleImage>> resultBean = imageClient.findImageByArticle(articleVo.getId());
            articleVo.setImageList(resultBean.getData());
        }
        return articleVoPage;
    }

    @Override
    public ArticleVo selectArticle(Long id) {
        ArticleVo articleVo = new ArticleVo();
        Article article = articleMapper.selectById(id);
        BeanUtils.copyProperties(article,articleVo);
        return articleVo;
    }

    @Override
    public List<ArticleVo> selectArticleWithLabel(Long id) {
        List<ArticleVo> list = articleMapper.selectArticleWithLabel(id);
        list.forEach(articleVo -> {
            articleVo.setLabelList(labelMapper.selectLabelWithArticle(articleVo.getId()));
        });
        return list;
    }

}
