package com.liaoxin.common.controller;

import com.alibaba.fastjson.JSON;
import com.liaoxin.common.RedisConst;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.service.RedisCacheService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BaseController {

    public Map userInfo(HttpServletRequest request){
        String userJson = request.getHeader("user");
        if(StringUtils.isEmpty(userJson) || StringUtils.isBlank(userJson)){
           throw new AppException("请先登录");
        }
        Map userMap = JSON.parseObject(userJson);
        return userMap;
    }

}
