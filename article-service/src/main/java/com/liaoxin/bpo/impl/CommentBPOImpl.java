package com.liaoxin.bpo.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liaoxin.bpo.CommentBPO;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.domain.Article;
import com.liaoxin.entity.CommentDomain;
import com.liaoxin.service.ArticleService;
import com.liaoxin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/16
 **/
@Service
@Transactional
public class CommentBPOImpl implements CommentBPO {

    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;

    public void deleteComment(Set commentIds){
        if(commentIds== null || commentIds.isEmpty()){
            throw new AppException("评论ID不能为空");
        }
        //删除评论表数据
        Wrapper commentWrapper = new UpdateWrapper<CommentDomain>()
                .set("status", WebConst.STATUS_0)
                .in("commentId",commentIds);
        commentService.update(commentWrapper);

        //查询评论对应的文章
        List<CommentDomain> commentList = commentService.list(new QueryWrapper<CommentDomain>().in("commentId",commentIds));
        List<Long> articleIdList = new ArrayList();
        for(CommentDomain commentDomain : commentList){
            articleIdList.add(commentDomain.getArticleId());
        }
        //更新文章表数据
        List<Article> articleList = articleService.list(new QueryWrapper<Article>().in("articleId",articleIdList));
        for(Article article : articleList){
            synchronized (this){
                if(article.getCommentNum() == 0){
                    throw new AppException("文章评论数错误");
                }
                article.setCommentNum(article.getCommentNum() - 1);
                articleService.updateById(article);
            }
        }
    }

}
