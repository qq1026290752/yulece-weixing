package com.yulece.weixing.utils;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * @author wangyichao@28ph.cn
 * @Title: DenerateUtil
 * @Package com.yulece.weixing.utils
 * @Description: 生成某些数据固定工具类
 * @Date 2018/4/14/21:30
 **/
public class GenerateUtil {

    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static synchronized String generateOrderId() {
        long nowTime = System.currentTimeMillis();
        Random random = new Random();
        int randomNum = random.nextInt(900000) + 100000;
        return new StringBuffer().append(nowTime).append(randomNum).toString();
    }

    public static void main(String[] args) {
        System.out.print(generateOrderId());

    }

}
