package com.imooc.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.utils.serializer.Data2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)//设置为null的字段不返回到结果中
public class OrderDTO {
    String orderId;
    String buyerName;
    String buyerAddress;
    String buyerPhone;
    String buyerOpenid;
    Integer orderStatus= OrderStatusEnum.NEW.getCode();
    Integer payStatus= PayStatusEnum.WAIT.getCode();
    BigDecimal orderAmount;
    //    创建时间
    @JsonSerialize(using = Data2LongSerializer.class)//自己写一个类用来转换日期的格式然后用注解导入
            Date createTime;
    //    更新时间
//    @JsonSerialize(using = Data2LongSerializer.class)
             Date updateTime;
    List<OrderDetail> orderDetailList=new ArrayList<>();
    //默认是一个空的list，可以避免返回为null,如果不需要返回就设置null就不返回
}
