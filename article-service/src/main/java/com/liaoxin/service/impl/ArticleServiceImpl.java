package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.client.ImageClient;
import com.liaoxin.client.UserClient;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.mapper.ArticleMapper;
import com.liaoxin.mapper.LabelMapper;
import com.liaoxin.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    LabelMapper labelMapper;
    @Autowired
    private ImageClient imageClient;
    @Autowired
    private UserClient userClient;

    @Override
    public Page<ArticleVo> selectArticleList(PageDTO pageDTO) {
        QueryWrapper<Article> wrapper = new QueryWrapper();
        wrapper.orderByDesc("create_time");
        Page<Article> articlePage =  articleMapper.selectPage(pageDTO,wrapper);

        List<ArticleVo> articleVoList = new ArrayList<>();
        for(Article article : articlePage.getRecords()){
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            articleVoList.add(articleVo);
        }

        Page<ArticleVo> articleVoPage = new Page<>(pageDTO.getCurrent(), articlePage.getSize(), articlePage.getTotal());
        articleVoPage.setRecords(articleVoList);

//        for(ArticleVo articleVo : articleVoPage.getRecords()){
//            ResultBean<UmsUser> resultBean = userClient.findUserById(articleVo.getUserId());
//            articleVo.setUmsUser(resultBean.getData());
//        }

//        for(ArticleVo articleVo : articleVoPage.getRecords()){
//            ResultBean<List<ArticleImage>> resultBean = imageClient.findImageByArticle(articleVo.getId());
//            articleVo.setImageList(resultBean.getData());
//        }

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

    public static void main(String[] args) throws IOException {
        System.out.println('a'+0);

    }

//    @Override
//    public List articleCountWith(String time) {
//        Map resultMap = new HashMap();
//        QueryWrapper<Article> wrapper = new QueryWrapper<>();
//        wrapper.groupBy("create_time");
//    }

}
