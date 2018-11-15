package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
@Autowired
private ProductServiceImpl productService;
    @Test
    public void findOne() {
        ProductInfo result=productService.findOne("123456");
        Assert.assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list=productService.findUpAll();
        Assert.assertNotEquals(0,list.size());
        System.out.println(list);
    }

    @Test
    public void findAll() throws Exception{
        PageRequest request=new PageRequest(0,2);
        Page<ProductInfo> productInfoPage=productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setProductStock(100);
        productInfo.setCategoryType(10);
        productService.save(productInfo);
        Assert.assertNotNull(productInfo);
    }
}