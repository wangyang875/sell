package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    success(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(20,"微信公众账号方面错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"微信异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(22,"订单取消成功！"),
    ORDER_CANCLE_FAIL(23,"订单取消失败！"),
    ORDER_FINISH_SUCCESS(24,"订单完结成功！"),
    PRODUCT_OFF_FAIL(25,"商品下架失败！"),
    PRODUCT_OFF_SUCCESS(26,"商品下架成功！"),
    PRODUCT_ON_FAIL(27,"商品上架失败！"),
    PRODUCT_ON_SUCCESS(28,"商品上架成功！"),
    PRODUCT_STATUS_ERROR(29,"商品状态不正确"),
    PRODUCT_INDEX_ADD_SUCCESS(30,"商品修改或添加成功！"),
    CATEGORY_INDEX_ADD_SUCCESS(31,"类目修改或添加成功！"),
    LOGIN_ERROR(32,"登录失败，登录信息不正确！"),
    LOGOUT_SUCCESS(33,"登出成功！")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
