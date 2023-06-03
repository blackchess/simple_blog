package com.liaoxin.filter;

import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.service.RedisCacheService;
import com.liaoxin.common.utils.JWTUtils;
import com.liaoxin.domain.WhiteListDomain;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Auther: liaoxin
 * @Date: 2023/5/16
 * @Description: 登录认证过滤器
 **/
@Component
public class AuthenticateFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateFilter.class);

    @Resource
    private WhiteListDomain whiteListDomain;
    @Resource
    RedisCacheService redisCacheService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().value();
        //判断请求是否在白名单内
        if(Objects.nonNull(whiteListDomain) && whiteListDomain.getWhiteList().contains(requestPath)){
            return chain.filter(exchange);
        }
        //Token鉴权
        String token = exchange.getRequest().getHeaders().getFirst("Bearer");
        if(Objects.isNull(token) || token ==""){
            throw new AppException(401, "未登录");
        }
        //登录信息过期
        try {
            JWTUtils.isExpired(token);
        }catch (JwtException e){
            LOGGER.error("Token:" + token + ",异常" + e.getMessage());
            redisCacheService.del(token);
            throw new AppException(401, "认证失败");
        }
        LOGGER.info("登录Token：", token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
