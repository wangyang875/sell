package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository repository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("12345678910");
        orderDetail.setOrderId("111112");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("1121211");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);
        OrderDetail result=repository.save(orderDetail);
        Assert.assertNotNull(orderDetail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> detailList=repository.findByOrderId("11111");
        Assert.assertNotEquals(0,detailList.size());
        System.out.println(detailList);
    }
}