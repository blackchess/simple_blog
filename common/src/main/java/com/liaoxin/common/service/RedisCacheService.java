package com.liaoxin.common.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

@Component
public class RedisCacheService implements ICacheService{

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, Long expire) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(expire));
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key,1);
    }
}
