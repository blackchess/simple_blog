package com.liaoxin.service;


import com.liaoxin.domain.UmsUser;
import com.liaoxin.model.vo.UmsUserVo;

public interface UserCacheService {

    /**
     * 获取缓存中的用户信息
     */
    UmsUser selectUser(String account);

    /**
     * 设置缓存中的用户信息
     */
    void setUser(UmsUserVo umsUser);

    /**
     * 删除缓存中的用户信息
     */
    Boolean deleteUser(String account);

    /**
     * 缓存验证码信息
     */
    void setCode(String account, String code);

    /**
     * 获取验证码
     */
    String selectCode(String account);

}
