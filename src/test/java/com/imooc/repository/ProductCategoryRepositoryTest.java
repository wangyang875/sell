package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
@Test
    public void findOneTest(){
    Optional<ProductCategory> productCategory=repository.findById(1);
    System.out.println(productCategory.toString());
}
@Test
@Transactional//让测试的数据不在数据库中保存，和service中的事务回滚不太一样
    public void saveTest(){
//    ProductCategory productCategory=repository.getOne(2);
//    productCategory.setCategoryType(10);
//    repository.save(productCategory);
    ProductCategory productCategory= new ProductCategory("男生最爱",4);
    ProductCategory result=repository.save(productCategory);
    Assert.assertNotNull(result);
}
@Test
    public void findByCategoryTypeInTest(){
    List<Integer> list= Arrays.asList(3,4,10);
    List<ProductCategory> result=repository.findByCategoryTypeIn(list);
    Assert.assertNotEquals(0,result.size());
}
}