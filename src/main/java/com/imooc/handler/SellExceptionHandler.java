package com.imooc.handler;

import com.imooc.exception.SellerAthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {
    //拦截登录异常
    @ExceptionHandler(value = SellerAthorizeException.class )
    public ModelAndView handlerAthorizeException(){
return new ModelAndView("redirect:"
        .concat("http://localhost:8080/sell/wechat/qrAthorize")
        .concat("?returnUrl=").concat("http://localhost:8080/sell/seller/login"));
    }
}
