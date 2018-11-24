package com.imooc.service;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    Page<ProductCategory> findAll(Pageable pageable);
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);
    ProductCategory save(ProductCategory productCategory);
}
