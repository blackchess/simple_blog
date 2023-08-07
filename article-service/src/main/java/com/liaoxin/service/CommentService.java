package com.liaoxin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.liaoxin.domain.CommentDomain;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/13
 **/
public interface CommentService extends IService<CommentDomain> {

    /**
     * 点赞和取消
     * @param isLike    是否点赞
     * @param commentId 评论ID
     * @return
     */
    CommentDomain changeLike(Boolean isLike, Long commentId);

}
