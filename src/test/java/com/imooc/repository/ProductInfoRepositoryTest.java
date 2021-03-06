package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
@Autowired
ProductInfoRepository repository;
@Test
public void saveTest() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("123456");
    productInfo.setProductName("皮蛋粥");
    productInfo.setProductPrice(new BigDecimal(3.2));
    productInfo.setProductDescription("很好喝的粥");
    productInfo.setProductIcon("http://xxxx.jpg");
    productInfo.setProductStatus(0);
    productInfo.setProductStock(100);
    productInfo.setCategoryType(10);
    ProductInfo result = repository.save(productInfo);
    Assert.assertNotNull(result);
}
    @Test
    public void findByProductStatus() {
    List<ProductInfo> list=repository.findByProductStatus(0);
    Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryType() {
        Page<ProductInfo> productInfoPage=repository.findByCategoryType(10, PageRequest.of(0,5 ));
        System.out.println(productInfoPage.getContent());
    }
}