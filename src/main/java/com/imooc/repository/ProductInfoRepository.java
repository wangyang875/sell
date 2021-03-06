package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
    ProductInfo findByProductId(String productId);
    Page<ProductInfo> findByCategoryType(Integer categoryType, Pageable pageable);
    List<ProductInfo> findByCategoryType(Integer categoryType);
}
