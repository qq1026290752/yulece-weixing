package com.yulece.weixing.dao;

import com.yulece.weixing.entity.OrderDetail;
import com.yulece.weixing.utils.GenerateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderDetailDaoTest
 * @Package com.yulece.weixing.dao
 * @Description:订单详情表接口测试
 * @Date 2018/4/14/21:43
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(GenerateUtil.generateUUID());
        orderDetail.setOrderId("4f3e6f85b3c94ca9a81f446f2b00f834");
        orderDetail.setProductId("41a5c7dd2b314b82aa9b5978b2909f3a");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductIcon("http://image.yulece.com/" + GenerateUtil.generateUUID() + ".jpg");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(1);
        OrderDetail orderDetailResult = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(orderDetailResult);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> resultDetail = orderDetailDao.findByOrderId("4f3e6f85b3c94ca9a81f446f2b00f834");
        Assert.assertEquals(2,resultDetail.size());
    }


}