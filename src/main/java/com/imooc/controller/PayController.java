package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //1、查询订单
        OrderDTO orderDTO = orderService.findone(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
//        PayResponse payResponse=payService.create(orderDTO);
        map.put("orderId", orderId);
        map.put("returnYrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }

    /**
     * 微信异步通知
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        /*
        返回给微信处理结果,这样在支付之后,微信就不会一直通知(订单已支付后通知会一致提示支付状态不正确,
        因为我们再支付之前写了判断支付状态的语句)
         */
        return new ModelAndView("pay/success");
    }
}
