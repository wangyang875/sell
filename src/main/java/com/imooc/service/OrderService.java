package com.imooc.service;

import com.imooc.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    //创建订单
    public OrderDTO create(OrderDTO orderDTO);
//    查询单个订单
    public OrderDTO findone(String orderId);
//    查询订单列表
    public Page<OrderDTO> findlist(String buyerOpenid, Pageable pageable);
//    取消订单
    public OrderDTO cancel(OrderDTO orderDTO);
//    完结订单
    public OrderDTO finish(OrderDTO orderDTO);
//    支付订单
    public OrderDTO paid(OrderDTO orderDTO);
}
