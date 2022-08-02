package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liaoxin.client.UserClient;
import com.liaoxin.common.ResultBean;
import com.liaoxin.domain.Article;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.domain.vo.ArticleVo;
import com.liaoxin.domain.vo.UserArticleVO;
import com.liaoxin.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserClient userClient;

    @GetMapping("")
    public ResultBean findArticlePage(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize){
        Page<ArticleVo> articleVoPage = articleService.selectArticleList(new PageDTO(pageNum,pageSize));
        return ResultBean.success(articleVoPage);
    }

    @GetMapping("{id}")
    public ResultBean findArticlePage(@PathVariable("id") Long id){
        ArticleVo result = articleService.selectArticle(id);
        return ResultBean.success("success",result);
    }

    @GetMapping("/label/{id}")
    public ResultBean<List<ArticleVo>> findArticleWithLabel(@PathVariable("id") Long id){
        List<ArticleVo> articleVoList = articleService.selectArticleWithLabel(id);
        return ResultBean.success(articleVoList);
    }

    @GetMapping("/user/{uid}")
    public ResultBean userArticleList(@PathVariable("uid") Long uid,
                                  @RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize){
        PageDTO<Article> pageDTO = new PageDTO<>(pageNum,pageSize);
        Page<Article> result = articleService.page(pageDTO, new QueryWrapper<Article>().eq("user_id",uid));
        List<ArticleVo> articleVoList = new ArrayList<>();
        ArticleVo articleVo = new ArticleVo();
        for(Article article : result.getRecords()){
            BeanUtils.copyProperties(article,articleVo);
            articleVoList.add(articleVo);
        }
        ResultBean<UmsUser> resultBean = userClient.findUserById(uid);
        UmsUser umsUser = resultBean.getData();
        UserArticleVO userArticleVO = new UserArticleVO();
        BeanUtils.copyProperties(umsUser,userArticleVO);
        userArticleVO.setArticleVoList(articleVoList);
        return ResultBean.success(userArticleVO);
    }

}
