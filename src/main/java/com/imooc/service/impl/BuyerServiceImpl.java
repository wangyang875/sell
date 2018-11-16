package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
       return checkOrderOwner(openid,orderid );
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {
        OrderDTO orderDTO=checkOrderOwner(openid,orderid );
        if (orderDTO==null){
            log.error("【取消订单】 查不到该订单， orderid={}", orderid);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return  orderService.cancel(orderDTO);

    }
    //判断是否是本人的订单
    private OrderDTO checkOrderOwner(String openid, String orderid){
        OrderDTO orderDTO=orderService.findone(orderid);
        if (orderDTO==null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】 订单的openid不一致。openid={},orderDTO={}", openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
