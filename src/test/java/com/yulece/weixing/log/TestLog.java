package com.yulece.weixing.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangyichao@28ph.cn
 * @Title: TestLog
 * @Package com.yulece.weixing.log
 * @Description:
 * @Date 2018/4/15/19:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestLog {

    @Test
    public void test(){
        log.info("这个是info级别的日志");
        log.debug("这个是debug级别的日志");
        log.warn("这个是warn级别的日志");
        log.error("这个是error级别的日志");
    }
}
