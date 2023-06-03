package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.utils.JWTUtils;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.service.UserCacheService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: liaoxin
 * @Date: 2022/9/30
 **/

@RestController
public class BaseController {

    @Resource
    UserCacheService userCacheService;

    /**
     * 通过Token获取用户信息
     * @param token
     * @return
     */
    public UmsUser userInfo(String token){
        if( StringUtils.isBlank(token) || JWTUtils.isExpired(token)){
            throw new AppException("用户凭证已失效，请重新登录");
        }
        String account = JWTUtils.deCodeStringFromToken(token);
        UmsUser umsUser = userCacheService.selectUser(account);
        return umsUser;
    }

}
