package com.imooc.utils;

import com.imooc.enums.CodeEnum;


public class EnumUtil {
    /**
     * 通用的一种方法，用于根据枚举的code，和类别，来返回特定的枚举
     * @param code
     * @param enumlass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumlass) {
        for (T each : enumlass.getEnumConstants()) {
            if (code.equals(each.getCode()))
                return each;
        }
        return null;
    }
}
