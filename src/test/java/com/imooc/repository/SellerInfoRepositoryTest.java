package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import com.imooc.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
@Autowired
SellerInfoRepository repository;
@Test
public void save(){
    SellerInfo sellerInfo=new SellerInfo();
    sellerInfo.setSellerId(KeyUtil.getUnique());
    sellerInfo.setUsername("admin");
    sellerInfo.setPassword("admin");
    sellerInfo.setOpenid("abc");
    SellerInfo result=repository.save(sellerInfo);
    Assert.assertNotNull(result);
}
    @Test
    public void findByOpenid() {
    SellerInfo sellerInfo=repository.findByOpenid("abc");
    Assert.assertNotNull(sellerInfo);
        System.out.println(sellerInfo.toString());
    }
}