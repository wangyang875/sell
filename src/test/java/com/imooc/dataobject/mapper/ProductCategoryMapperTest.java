package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {
@Autowired
private ProductCategoryMapper mapper;
    @Test
    public void insertByMap() {
        Map<String,Object> map=new HashMap<>();
        map.put("category_name","师兄最爱" );
        map.put("category_type",11 );
        mapper.insertByMap(map);
    }
    @Test
    public void insertByObject(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("师姐最爱");
        productCategory.setCategoryType(13);
        mapper.insertByObject(productCategory);
    }

    @Test
    public void findByCategoryType() {
        Integer categoryType=12;
        ProductCategory productCategory=mapper.findByCategoryType(categoryType);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void updateByCategoryType() {
        int result=mapper.updateByCategoryType(13,"师兄最不爱" );
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("师姐最爱");
        productCategory.setCategoryType(13);
        int result=mapper.updateByObject(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteByCategoryType() {
        int result=mapper.deleteByCategoryType(13);
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory productCategory=mapper.selectByCategoryType(12);
        System.out.println(productCategory.toString());
        Assert.assertNotNull(productCategory);
    }
}