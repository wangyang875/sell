package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data//包括get\set方法和tostring方法
@Proxy(lazy = false)
public class ProductInfo {
    @Id
    private String productId;
    private String productName;//名字
    private BigDecimal productPrice;//单价
    private Integer productStock;//库存
    private String productDescription;//描述
    private String productIcon;//商品小图
    private Integer productStatus=ProductStatusEnum.UP.getCode();//状态：0正常，1下架
    private Integer categoryType;//类目编号
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    public ProductStatusEnum getproductStatusEnum(){
        return ProductStatusEnum.getProductStatusEnum(productStatus);
    }
}
