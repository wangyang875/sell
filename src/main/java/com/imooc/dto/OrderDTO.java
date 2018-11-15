package com.imooc.dto;

import com.imooc.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderDTO {
    String orderId;
    String buyerName;
    String buyerAddress;
    String buyerPhone;
    String buyerOpenid;
    Integer orderStatus;
    Integer payStatus;
    BigDecimal orderAmount;
    List<OrderDetail> orderDetailList;
}
