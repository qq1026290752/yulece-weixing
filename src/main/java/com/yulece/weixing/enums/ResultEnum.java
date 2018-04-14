package com.yulece.weixing.enums;
import lombok.Getter;

/**
 * @author wangyichao@28ph.cn
 * @Title: ResultEnum
 * @Package com.yulece.weixing.enums
 * @Description:
 * @Date 2018/4/1415:06
 **/
public enum ResultEnum{
    SUCCESS(0,"success"),
    ERROR(1,"error")
    ;

    private int code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
