package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.client.ImageClient;
import com.liaoxin.client.UserClient;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.mapper.ArticleMapper;
import com.liaoxin.mapper.LabelMapper;
import com.liaoxin.service.ArticleCacheService;
import com.liaoxin.service.ArticleImageService;
import com.liaoxin.service.ArticleService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    LabelMapper labelMapper;
    @Resource
    ArticleCacheService articleCacheService;
    @Resource
    ArticleImageService articleImageService;
    @Resource
    private UserClient userClient;

    @Override
    public Page<ArticleVo> selectArticleList(PageDTO pageDTO, String keyword, Long userId) {
        //查询文章信息
        QueryWrapper wrapper = new QueryWrapper<Article>();
        if(!ObjectUtils.isEmpty(userId)){
            wrapper.eq("user_id", userId);
        }
        wrapper.orderByDesc("create_time");
        Page<Article> articlePage = articleMapper.selectPage(pageDTO, wrapper);
        //查询文章用户信息
        List<ArticleVo> articleVoList = new ArrayList<>();
        for(Article article : articlePage.getRecords()){
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(article, articleVo);
            ResultBean<UmsUser> resultBean = userClient.findUserById(articleVo.getUserId());
            if(WebConst.STATUS_Code_200.equals(resultBean.getCode())){
                articleVo.setUmsUser(resultBean.getData());
            }else{
                throw new AppException("获取用户信息异常");
            }
            articleVoList.add(articleVo);
        }
        Page<ArticleVo> articleVoPage = new Page<>(articlePage.getCurrent(), articlePage.getSize(), articlePage.getTotal());
        articleVoPage.setRecords(articleVoList);
        return articleVoPage;
    }

    @Override
    public Page<Article> selectHotArticle(PageDTO pageDTO, String order) {
        QueryWrapper wrapper = new QueryWrapper();
        if(WebConst.STATUS_0.equals(order)){
            wrapper.orderByAsc("view_num");
        }else{
            wrapper.orderByDesc("view_num");
        }
        Page<Article> articlePage = articleMapper.selectPage(pageDTO, wrapper);
        for(Article article : articlePage.getRecords()){
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(article, articleVo);
            //热点数据存入缓存
            articleCacheService.setArticleCache(articleVo);
        }
        return articlePage;
    }

    @Override
    public ArticleVo selectArticle(Long id) {
        //先查缓存，没有再查数据库
        ArticleVo articleVo = new ArticleVo();
        articleCacheService.selectArticleCache(id);
        if(ObjectUtils.isEmpty(articleVo) || articleVo.getId() == null){
            Article article = articleMapper.selectById(id);
            BeanUtils.copyProperties(article,articleVo);
        }
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
