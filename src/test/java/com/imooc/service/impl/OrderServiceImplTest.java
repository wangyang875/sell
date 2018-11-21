package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private final String BUYER_OPENID="1101110";
    private final String ORDER_ID="11111";

    @Test
    public void create() throws Exception{
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("1234567891230");
        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(3);
        OrderDetail o2=new OrderDetail();
        o2.setProductId("1234568");
        o2.setProductQuantity(2);
        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result=orderService.create(orderDTO);
        System.out.println(orderDTO);

    }

    @Test
    public void findone() {
        OrderDTO orderDTO=orderService.findone("1542112917249405292");
        System.out.println(orderDTO);
    }

    @Test
    public void findlist() {
        PageRequest request=new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage=orderService.findlist(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getSize());
        System.out.println(orderDTOPage.getContent());
    }

    @Test
    public void cancel() throws Exception{
          OrderDTO orderDTO=orderService.findone("1542112917249405292");
          OrderDTO result=orderService.cancel(orderDTO);
          Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.findone("1542112917249405292");
        OrderDTO result=orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO=orderService.findone("1542112917249405292");
        OrderDTO result=orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCESS.getCode(),result.getPayStatus());
    }
    @Test
    public void list(){
        PageRequest pageRequest=new PageRequest(0, 7);
        Page<OrderDTO> orderDTOpage=orderService.findlist(pageRequest);
        log.info("orderdtoPage={}", orderDTOpage.getContent());
        Assert.assertNotEquals(0, orderDTOpage.getTotalElements() );
    }
}