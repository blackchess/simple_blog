package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.service.ArticleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articleImage")
public class ArticleImageController {

    @Autowired
    ArticleImageService articleImageService;

    @GetMapping("/{id}")
    public ResultBean findArticleImage(@PathVariable("id") Long id){
        ArticleImage result = articleImageService.getById(id);
        return ResultBean.success("success",result);
    }

    @PostMapping("")
    public ResultBean addNewArticleImage(@RequestBody ArticleImage articleImage){
         boolean result = articleImageService.saveOrUpdate(articleImage);
         return ResultBean.success("success",result);
    }

    @GetMapping("/article/{id}")
    public ResultBean findImageByArticle(@PathVariable("id") Long id){
        QueryWrapper<ArticleImage> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id",id);
        List<ArticleImage> result = articleImageService.list(wrapper);
        return ResultBean.success("success",result);
    }
}
