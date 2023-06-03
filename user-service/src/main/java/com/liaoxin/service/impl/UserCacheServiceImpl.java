package com.liaoxin.service.impl;


import com.liaoxin.common.RedisConst;
import com.liaoxin.common.service.RedisCacheService;
import com.liaoxin.domain.UmsUser;
import com.liaoxin.model.vo.UmsUserVo;
import com.liaoxin.service.UserCacheService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Resource
    RedisCacheService redisCacheService;

    @Override
    public UmsUser selectUser(String account) {
        String key = RedisConst.REDIS_UMS_DATABASE + ":" + RedisConst.REDIS_UMS_PREFIX + ":" + account;
        return (UmsUser) redisCacheService.get(key);
    }

    @Override
    public void setUser(UmsUserVo umsUserVo) {
        String key = RedisConst.REDIS_UMS_DATABASE + ":" + RedisConst.REDIS_UMS_PREFIX + ":" + umsUserVo.getAccount();
        redisCacheService.set(key, umsUserVo, RedisConst.REDIS_UMS_EXPIRE);
    }

    @Override
    public Boolean deleteUser(String account) {
        String key = RedisConst.REDIS_UMS_DATABASE + ":" + RedisConst.REDIS_UMS_PREFIX + ":" + account;
        return redisCacheService.del(key);
    }

    @Override
    public void setCode(String account, String code) {
        String key = RedisConst.REDIS_UMS_DATABASE + ":" + RedisConst.REDIS_CODE_PREFIX + ":" + account;
        redisCacheService.set(key, code, RedisConst.REDIS_UMS_EXPIRE);
    }

    @Override
    public String selectCode(String account) {
        String key = RedisConst.REDIS_UMS_DATABASE + ":" + RedisConst.REDIS_CODE_PREFIX + ":" + account;
        return (String) redisCacheService.get(key);
    }
}
