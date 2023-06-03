package com.liaoxin.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.utils.JWTUtils;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.service.UserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/22
 **/
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    UserCacheService userCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String token = request.getHeader("Bearer");
//        if(StringUtils.isBlank(token)){
//            throw new AppException("请先登录");
//        }
//        if(JWTUtils.isExpired(token)){
//            throw new AppException("凭证已失效");
//        }
//        String account = JWTUtils.deCodeStringFromToken(token);
//        UmsUser umsUser = userCacheService.selectUser(account);
//        if(request.getRequestURL().indexOf("admin") > 0 && umsUser.getRole() != 1) {
//            LOGGER.error("Authentication error: " + umsUser);
//            return false;
//        }
        return true;
    }
}
