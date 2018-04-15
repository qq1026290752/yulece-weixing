package com.yulece.weixing.enums;

/**
 * @author wangyichao@28ph.cn
 * @Title: ExceptionEnum
 * @Package com.yulece.weixing.enums
 * @Description: 异常处理枚举类
 * @Date 2018/4/14/22:41
 **/
public enum  ExceptionEnum {
    PRODUCT_INEXISTENCE(0,"商品信息不存在"),
    PRODUCT_STOCK_INSUFFICIENT(1,"商品库存不足"),
    ORDER_NOT_ERROR(10,"订单不存在"),
    ORDER_DETAIL_NOT_ERROR(11,"订单详情不存在"),
    ORDER_STATUS_ERROR(12,"订单状态不正确"),
    ORDER_UPDATE_ERROR(13,"订单更新异常"),
    ORDER_DETAIL_NOT_NULL(14,"订单详情不存在,不用返还库存"),
    ORDER_PAY_WAIT_ERROR(15,"订单未支付状态"),
    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
