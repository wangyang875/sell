package com.imooc.utils;

import com.imooc.constant.RedisConstant;
import com.imooc.repository.SellerInfoRepository;
import org.apache.http.protocol.RequestUserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获得当前操作的用户名，时间，和客户端的相关信息
 */
@Component
public class GetUserActions {
    @Autowired
    StringRedisTemplate redisTemplate=new StringRedisTemplate();
    @Autowired
    SellerInfoRepository sellerInfoRepository;

    public String[] getUserName(HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, "token");
        String value = cookie.getValue();
        String openid = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, value));
        String username = sellerInfoRepository.findByOpenid(openid).getUsername();
        String[] userInfo = new String[3];
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userAgent = request.getHeader("User-Agent");
        userInfo[0] = username;//用户名
        userInfo[1] = ft.format(new Date());//时间
        userInfo[2] = userAgent;//客户端信息
        return userInfo;
    }
}
