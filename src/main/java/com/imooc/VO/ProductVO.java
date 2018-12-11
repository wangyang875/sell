package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
商品，（包含类目）
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 7097863777546530545L;
    @JsonProperty("name")//返回显示的是name
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
