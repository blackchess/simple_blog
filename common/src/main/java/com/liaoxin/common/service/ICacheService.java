package com.liaoxin.common.service;

/**
 * 缓存操作类
 */
public interface ICacheService<T> {

    /**
     * 设置缓存
     */
    void set(String key, Object value);

    /**
     * 设置带失效时间的缓存
     */
    void set(String key, Object value, Long expire);

    /**
     * 获取指定缓存信息
     */
    Object get(String key);

    /**
     * 删除指定缓存信息
     */
    Boolean del(String key);

    /**
     * 减少缓存对应的数量
     */
    Long decr(String key);

}
