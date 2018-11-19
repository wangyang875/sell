package com.imooc.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig {
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    //把你要实例化的对象转化成一个Bean，放在IoC容器中
    @Bean
    public BestPayServiceImpl bestPayService() {

        //支付类, 所有方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    private WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());//设置微信公众号的appid
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());// 设置微信公众号的app corpSecret
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());// 设置商户号
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());// 设置商户密钥
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());// 设置商户证书路径
        wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());// 设置支付后异步通知url
        return wxPayH5Config;
    }

}

