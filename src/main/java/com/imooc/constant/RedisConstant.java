package com.imooc.constant;

public interface RedisConstant {
    /*
    key的前缀
     */
    String TOKEN_PREFIX="token_%s";
    /*
    redis缓存过期时间，2小时
     */
    Integer EXPIRE=7200;
}
