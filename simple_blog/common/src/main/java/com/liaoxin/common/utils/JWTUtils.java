package com.liaoxin.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/13
 * @Description: JWT工具类
 **/
public class JWTUtils {

    //过期时间
    private static final long EXPIRATION_TIME = 1000 * 60 * 20;

    //加密因子
    private static final String APP_SECRET= "simple_BLOGasdfadswwwg#ererewrdsf";


    /**
     * 生成Token
     * @param claims JWT负载
     */
    private static String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)),SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Token负载字符串
     * @param sub
     */
    public static String generateToken(String sub){
        Map<String, Object> claims = new HashMap();
        claims.put("sub", sub);
        claims.put("createDate", new Date());
        return generateToken(claims);
    }

    /**
     * Token解析字符串
     * @param token
     * @return
     */
    public static String deCodeStringFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 判断Token是否过期
     * @param token
     * @return
     */
    public static Boolean isExpired(String token){
        Claims claims = getClaimsFromToken(token);
        Date expiredDate = claims.getExpiration();
        return expiredDate.before(new Date());
    }

}
