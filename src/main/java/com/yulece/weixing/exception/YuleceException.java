package com.yulece.weixing.exception;

import com.yulece.weixing.enums.ExceptionEnum;

/**
 * @author wangyichao@28ph.cn
 * @Title: YuleceException
 * @Package com.yulece.weixing.exception
 * @Description: 异常信息处理类
 * @Date 2018/4/14/22:38
 **/
public class YuleceException extends RuntimeException{

    private Integer code;

    public YuleceException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

}
