package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    Suceess(0,"成功"),
    Fail(1,"失败");

    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
