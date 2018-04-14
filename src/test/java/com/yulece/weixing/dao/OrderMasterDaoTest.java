package com.yulece.weixing.dao;

import com.yulece.weixing.entity.OrderMaster;
import com.yulece.weixing.utils.GenerateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderMasterDaoTest
 * @Package com.yulece.weixing.dao
 * @Description:订单接口持久层测试
 * @Date 2018/4/14/20:41
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void createOrder(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(GenerateUtil.generateUUID());
        orderMaster.setBuyerName("小瘸子");
        orderMaster.setBuyerAddress("叶青大厦北园");
        orderMaster.setBuyerOpenid(GenerateUtil.generateUUID());
        orderMaster.setBuyerPhone("15311066666");
        orderMaster.setOrderAmount(new BigDecimal(10.5));
        orderMasterDao.save(orderMaster);
    }
}