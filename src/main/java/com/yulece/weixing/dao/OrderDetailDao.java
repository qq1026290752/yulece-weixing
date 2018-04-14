package com.yulece.weixing.dao;

import com.yulece.weixing.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderDetailDao
 * @Package com.yulece.weixing.dao
 * @Description:
 * @Date 2018/4/14/21:17
 **/
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    /*根据订单Id查询订单*/
    List<OrderDetail> findByOrderId(String orderId);
}
