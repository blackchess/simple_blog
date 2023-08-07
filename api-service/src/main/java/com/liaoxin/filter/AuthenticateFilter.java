package com.liaoxin.filter;

import com.alibaba.fastjson.JSON;
import com.liaoxin.common.RedisConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.service.RedisCacheService;
import com.liaoxin.common.utils.JWTUtils;
import com.liaoxin.domain.WhiteListDomain;
import io.jsonwebtoken.JwtException;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
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
@Order(10)
public class AuthenticateFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateFilter.class);

    @Resource
    private WhiteListDomain whiteListDomain;
    @Resource
    RedisCacheService redisCacheService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().value();
        //判断请求是否在白名单内
        if(Objects.nonNull(whiteListDomain.getWhiteList())){
            PathMatcher pathMatcher = new AntPathMatcher();
            for(String whitePath : whiteListDomain.getWhiteList()){
                if(pathMatcher.match(whitePath, requestPath)){
                    return chain.filter(exchange);
                }
            }
        }
        //Token鉴权
        String token = exchange.getRequest().getHeaders().getFirst("Bearer");
        if(Objects.isNull(token) || token == ""){
            throw new AppException(401, "未登录");
        }
        //登录信息过期
        try {
            JWTUtils.isExpired(token);
        }catch (JwtException e){
            LOGGER.error("Token:" + token + ",异常" + e.getMessage());
            throw new AppException(401, "认证失败");
        }
        LOGGER.info("登录Token：" + token);
        //添加用户信息
        String account = JWTUtils.deCodeStringFromToken(token);
        String key = RedisConst.REDIS_UMS_DATABASE + ":" + RedisConst.REDIS_UMS_PREFIX + ":" + account;
        String userJson = JSON.toJSONString(redisCacheService.get(key));
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.add("user", userJson);
        }).build();
        exchange.mutate().request(serverHttpRequest);
        return chain.filter(exchange);
    }
}
