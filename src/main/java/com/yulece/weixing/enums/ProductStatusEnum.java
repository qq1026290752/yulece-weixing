package com.yulece.weixing.enums;

import lombok.Getter;

/**
 * @author wangyichao@28ph.cn
 * @Description: 商品状态枚举类
 * @date 2018/4/1318:56
 **/
public enum  ProductStatusEnum {
    UP(0,"在售商品"),
    DOWN(1,"下架商品")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
