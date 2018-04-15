package com.yulece.weixing.service.impl;

import com.yulece.weixing.dto.OrderDto;
import com.yulece.weixing.entity.OrderDetail;
import com.yulece.weixing.service.OrderService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderServiceImplTest
 * @Package com.yulece.weixing.service.impl
 * @Description:
 * @Date 2018/4/15/10:27
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Test
    public void createOderMaster() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress("中国移动大厦");
        orderDto.setBuyerName("10086");
        orderDto.setBuyerOpenid("934f49d4e37b42f8b3acdf82ce2f88e2");
        orderDto.setBuyerPhone("15311066666");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("2f49d4d930ae47c7b1649f89077ab736");
        orderDetail.setProductQuantity(1);
        OrderDetail detail = new OrderDetail();
        detail.setProductId("72bd0d0b15b040dcba65ca42ca8d4c81");
        detail.setProductQuantity(5);
        ArrayList<OrderDetail> orderDetails = Lists.newArrayList(orderDetail, detail);
        orderDto.setOrderDetails(orderDetails);
        OrderDto master = orderService.createOderMaster(orderDto);
        LOGGER.info("[创建订单] result = {}", master);
    }

    @Test
    public void findById() {
        OrderDto orderDto = orderService.findById("1523761104507593465");
        Assert.assertNotNull(orderDto);

    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<OrderDto> orderDtoPages = orderService.findAll("934f49d4e37b42f8b3acdf82ce2f88e2", pageRequest);
        Assert.assertEquals(1,orderDtoPages.getTotalPages());
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findById("1523775160775312197");
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertNotNull(result);
    }

    @Test
    public void finish() {
    }

    @Test
    public void pay() {
    }
}