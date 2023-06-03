package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.entity.CommentDomain;
import com.liaoxin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/13
 * @Description: 文章评论控制器
 **/
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 获取评论列表
     * @param articleId 文章ID
     * @param current   当前页数
     * @param size      分页大小
     */
    @GetMapping("{isParent}")
    public ResultBean<Page<CommentDomain>> fetchCommentList(@PathVariable("isParent") Boolean isParent,
                                                            @RequestParam(value = "parentId",required = false) Long parentId,
                                                            @RequestParam("articleId") Long articleId,
                                                            @RequestParam("current") Long current,
                                                            @RequestParam("size") Long size){
        if(isParent && parentId == null){
            throw new AppException("未查到对应的父评论信息");
        }
        PageDTO pageDTO = new PageDTO<CommentDomain>(current,size);
        QueryWrapper wrapper = new QueryWrapper<CommentDomain>()
                .eq("status", WebConst.STATUS_1)
                .eq("article_id", articleId);
        //判断是否查询父评论
        if(isParent){
            wrapper.isNotNull("parent_id");
        }else{
            wrapper.isNull("parent_id");
        }
        return ResultBean.success(commentService.page(pageDTO, wrapper));
    }

    /**
     * 点赞和取消点赞评论
     * @param commentId 评论ID
     * @param isLike    是否点赞
     * @return
     */
    @PutMapping("like/{commentId}")
    public ResultBean<CommentDomain> changeLike(@PathVariable("commentId") Long commentId,
                                                @RequestParam("isLike") Boolean isLike){
        if(isLike == null){
            throw new AppException("参数异常");
        }

        CommentDomain commentDomain = commentService.changeLike(isLike, commentId);
        return ResultBean.success(commentDomain);
    }

    /**
     * 根据评论ID获取评论信息
     * @param id    评论ID
     */
    @GetMapping("{id}")
    public ResultBean<CommentDomain> fetchComment(@PathVariable("id") Long id){
        Wrapper wrapper = new QueryWrapper<CommentDomain>()
                .eq("id",id)
                .eq("status",WebConst.STATUS_1);
        CommentDomain commentDomain = commentService.getOne(wrapper);
        return ResultBean.success(commentDomain);
    }

}
