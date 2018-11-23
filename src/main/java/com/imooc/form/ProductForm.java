package com.imooc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductForm {
    private String productId;
    @NotEmpty(message = "商品名不能为空")
    private String productName;
    @NotNull( message = "商品价格不能为空")
    private BigDecimal productPrice;
    @NotNull(message = "库存不能为空")
    private Integer productStock;
    @NotNull(message = "品类不能为空")
    private Integer categoryType;
    private String productDescription;
    private String productIcon;
}
