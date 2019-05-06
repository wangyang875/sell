package com.imooc.controller;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.dataobject.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.SellerService;
import com.imooc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map,
                              HttpServletRequest request) {
        //1、openid和数据库里的openid匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_ERROR.getMsg());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/common/error");
        }

        //2、设置token至redis
        String token= UUID.randomUUID().toString();
        Integer expire= RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token), openid,expire, TimeUnit.SECONDS);
        //3、设置token至浏览器cookie
        CookieUtil.set(response, "token", token, CookieConstant.EXPIRE);
        String username=sellerInfoRepository.findByOpenid(openid).getUsername();
        String userAgent=request.getHeader("User-Agent");
//        String[] userInfo=new GetUserActions().getUserName(request,redisTemplate,sellerInfoRepository);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        log.info(username+","+ft.format(new Date())+",登录成功,"+userAgent);
        //跳转的时候用完整的http路径
        return new ModelAndView("redirect:/seller/order/list");//从sell后面开始接，为避免跳转出错可以使用绝对地址
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //1、从cookie里查询
       Cookie cookie= CookieUtil.get(request,"token");
       if (cookie!=null){
           //2、清除redis
           redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));

//           String[] userinfo=new GetUserActions().getUserName(request,redisTemplate,sellerInfoRepository);
           //3、清除cookie
           CookieUtil.set(response, CookieConstant.TOKEN,null , 0);
//           log.info(userinfo[0]+","+new GetTime().getTime()+",注销成功,"+userinfo[1]);

       }
       map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
       map.put("url","/sell/seller/order/list" );
       return new ModelAndView("/common/success",map);
    }
}
