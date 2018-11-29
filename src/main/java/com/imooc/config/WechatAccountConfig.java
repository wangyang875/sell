package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /*
    公众平台id
     */
    private String mpAppId ;
    /*
    公共平台密钥
     */
    private String mpAppSecret;
    /*
    开放平台id
     */
    private String openAppId ;
    /*
    开放平台密钥
     */
    private String openAppSecret;
    private String mchId;
    private String mchKey;
    private String KeyPath;
    private String notifyUrl;
}
