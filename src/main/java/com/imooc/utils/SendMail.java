package com.imooc.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
public class SendMail {
    public SimpleMailMessage sendSimpleMail(String authCode,String email) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wangyang875@163.com");
        message.setTo(email);
        message.setSubject("请及时输入本次修改密码的验证码");
        message.setText(authCode);
//        mailSender.send(message);
        return message;
    }
}
