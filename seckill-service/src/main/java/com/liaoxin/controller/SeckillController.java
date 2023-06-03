package com.liaoxin.controller;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.service.RedisCacheService;
import com.liaoxin.domain.UmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: liaoxin
 * @Date: 2023/1/11
 **/
@RestController
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    RedisCacheService redisCacheService;

    /**
     * 处理秒杀请求
     * @param id 商品ID
     * @param user 用户
     */
    @PostMapping("/{id}")
    public ResultBean handleSeckill(@PathVariable("id") Long id, UmsUser user){
        Long stock = redisCacheService.decr(String.valueOf(id));
        if(stock < 0){
            return ResultBean.failure(501,"该商品已买完");
        }
        return ResultBean.success();
    }

}
