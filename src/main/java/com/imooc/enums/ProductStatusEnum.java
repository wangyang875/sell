package com.imooc.enums;

import com.imooc.utils.EnumUtil;
import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0, "在架"),
    DOWN(1, "下架");
    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static ProductStatusEnum getProductStatusEnum(Integer code){
        return EnumUtil.getByCode(code, ProductStatusEnum.class);
    }
}
