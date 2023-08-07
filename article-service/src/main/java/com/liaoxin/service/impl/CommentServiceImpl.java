package com.liaoxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.domain.CommentDomain;
import com.liaoxin.mapper.CommentMapper;
import com.liaoxin.service.CommentService;
import com.liaoxin.service.lock.CompareAndSwap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/13
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentDomain> implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public CommentDomain changeLike(Boolean isLike, Long commentId) {
        CommentDomain commentDomain = commentMapper.selectById(commentId);
        if(commentDomain == null) {
            throw new AppException("没有找到该该评论信息");
        }
        Integer likeNum = commentDomain.getLikeNum();
        //ReentrantLock实现锁
        CompareAndSwap compareAndSwap = new CompareAndSwap(likeNum);
        if(isLike){
            compareAndSwap.compareAndSet(likeNum,likeNum + 1);
            likeNum++;
        }else{
            compareAndSwap.compareAndSet(likeNum,likeNum - 1);
            if(likeNum == 0){
                throw new AppException("系统异常");
            }
            likeNum--;
        }
        Lock reentrantLock = new ReentrantLock(true);
        commentDomain.setLikeNum(likeNum);
        commentMapper.updateById(commentDomain);
        //锁对象（请求慢需要修改）
//        synchronized (this){
//            if(isLike){
//                likeNum++;
//            }else{
//                if(likeNum == 0){
//                    throw new AppException("系统异常");
//                }
//                likeNum--;
//            }
//            commentDomain.setLikeNum(likeNum);
//            commentMapper.updateById(commentDomain);
//        }
        return commentDomain;
    }


}
