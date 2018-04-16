package com.yulece.weixing.vo;

import com.yulece.weixing.enums.ResultEnum;

/**
 * @author wangyichao@28ph.cn
 * @Title: ResultVo
 * @Package com.yulece.weixing.vo
 * @Description: http rest 返回对象
 * @Date 2018/4/1414:56
 **/
public class ResultVo<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private ResultVo(Integer code,String message,T data){
        this.code = code;
        this.data = data;
        this.message = message;
    }
    private ResultVo(Integer code){
        this.code = code;
    }

    public static <T> ResultVo<T> createSuccessResult(String message,T data){
        return new ResultVo(ResultEnum.SUCCESS.getCode(),message,data);
    }

    public static <T> ResultVo<T> createSuccessResult(Integer code){
        return new ResultVo(code);
    }
    public static <T> ResultVo<T> createErrorResult(String message,T data){
        return new ResultVo(ResultEnum.ERROR.getCode(),message,data);
    }
    public static <T> ResultVo<T> createSuccessResult(T data){
        return new ResultVo(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),data);
    }
    public static <T> ResultVo<T> createErrorResult(T data){
        return new ResultVo(ResultEnum.ERROR.getCode(),ResultEnum.SUCCESS.getMessage(),data);
    }
    public static <T> ResultVo<T> createErrorResult(Integer code){
        return new ResultVo(code);
    }
}
