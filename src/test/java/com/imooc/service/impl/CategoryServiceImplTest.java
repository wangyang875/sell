package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        categoryService.findOne(1);
    }

    @Test
    public void findAll() {
        List<ProductCategory> list=categoryService.findAll();
    }

    @Test
    public void findByCategoryTypeIn() {
       List<ProductCategory>  list=categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
    }

    @Test
    public void save() {
        categoryService.save(new ProductCategory("男生最爱",4));
    }
}