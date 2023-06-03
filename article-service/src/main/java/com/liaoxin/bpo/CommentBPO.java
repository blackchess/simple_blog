package com.liaoxin.bpo;

import java.util.Set;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/16
 **/
public interface CommentBPO {

    /**
     * 删除评论操作
     * @param commentIds 评论ID
     */
    void deleteComment(Set commentIds);

}
