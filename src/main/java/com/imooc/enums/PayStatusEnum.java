package com.imooc.enums;

import com.imooc.utils.EnumUtil;
import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCESS(1,"支付成功");
    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static PayStatusEnum getPayStatusEnum(Integer code){
       return EnumUtil.getByCode(code,PayStatusEnum.class );
    }
}
