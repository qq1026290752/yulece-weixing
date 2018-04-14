package com.yulece.weixing.utils;

import java.util.UUID;

/**
 * @author wangyichao@28ph.cn
 * @Title: DenerateUtil
 * @Package com.yulece.weixing.utils
 * @Description: 生成某些数据固定工具类
 * @Date 2018/4/14/21:30
 **/
public class GenerateUtil {

    public static  String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
