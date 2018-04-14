package com.yulece.weixing.enums;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderEnum
 * @Package com.yulece.weixing.enums
 * @Description: 订单枚举类
 * @Date 2018/4/14/20:43
 **/
public class OrderEnum {

    public enum OrderStatusEnum{
        NEW(0,"创建订单"),
        FINISH(1,"完结"),
        CANCEL(2,"取消");

        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        OrderStatusEnum(Integer code,String message){
            this.code = code;
            this.message = message;
        }
    }
    public enum PayStatusEnum{
        WAIT(0,"未支付"),
        SUCCESS(1,"完结");

        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        PayStatusEnum(Integer code,String message){
            this.code = code;
            this.message = message;
        }
    }

}
