package com.imooc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
//    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Autowired
StringRedisTemplate redisTemplate;
    @Test
    public void test1() {
        String name="imooc";
        String password="123456";
        log.debug("debug...");
        log.info("info...");
        log.error("error...");
        log.info("name:"+name+" password:"+password);
        log.info("name: {},password:{}",name,password);
    }
    @Test
    public void testRedis(){
        String aa=redisTemplate.opsForValue()
                .get("token_e861196d-e2d3-41db-8aa8-ea26689edd3a");
        System.out.println(aa);
    }
}
