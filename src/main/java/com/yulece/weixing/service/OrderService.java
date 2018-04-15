package com.yulece.weixing.service;

import com.yulece.weixing.dto.OrderDto;
import com.yulece.weixing.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderService
 * @Package com.yulece.weixing.service
 * @Description: 订单服务接口
 * @Date 2018/4/14/22:03
 **/
public interface OrderService {

    /**
     * 创建订单
     */
    OrderDto createOderMaster(OrderDto orderDto);

    /**
     * 查询某个订单
     */
    OrderDto findById(String orderId);
    /**
     * 查询订单列表
     */
    Page<OrderDto> findAll(String buyerOpenId, Pageable pageable);
    /**
     * 取消订单
     */
    OrderDto cancel(OrderDto orderDto);
    /**
     * 完结订单
     */
    OrderDto finish(OrderDto orderDto);

    /**
     * 支付订单
     */
    OrderDto pay(OrderDto orderDto);


}
