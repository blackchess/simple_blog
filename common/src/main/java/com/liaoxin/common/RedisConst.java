package com.liaoxin.common;

public class RedisConst {

    //用户缓存过期时间
    public static final Long REDIS_UMS_EXPIRE = 6400L;
    //文章缓存过期时间
    public static final Long REDIS_ARTICLE_EXPIRE = 6400L;

    //用户缓存数据库
    public static final String REDIS_UMS_DATABASE = "UMS";

    //用户缓存前缀
    public static final String REDIS_UMS_PREFIX = "USER";

    //验证码缓存前缀
    public static final String REDIS_CODE_PREFIX = "CODE";

    //内容缓存数据库
    public static final String REDIS_CMS_DATABASE = "CMS";

    //文章缓存前缀
    public static final String REDIS_CMS_PREFIX = "ARTICLE";
}
