package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    Page<ProductInfo> findByCategoryType(Integer categoryType,Pageable pageable);
    List<ProductInfo> findByCategoryType(Integer categoryType);
    /*
    商品上下架
     */
    ProductInfo off_sale(String productId);
    ProductInfo on_sale(String productId);
    //加库存
void increaseStock(List<CartDTO> cartDTOList);
    //减库存
void decreaseStock(List<CartDTO> cartDTOList);
}
