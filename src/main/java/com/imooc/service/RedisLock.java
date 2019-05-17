package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;//可以设置就返回true
        }
        //curentValue=A,线程1和线程2进来，currentTime+TimeOut=B
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间,同时将自己的时间设置进去，这个操作同一时间只会有一个线程操作
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            //保证线程1和线程不会都获得锁
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key,String value){
        try {
        String currentValue=redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue)&& currentValue.equals(value)){
            redisTemplate.opsForValue().getOperations().delete(key);
        }}
        catch (Exception e){
            log.error("【redis分布式锁】 解锁异常，{}",e );
        }
    }
}
