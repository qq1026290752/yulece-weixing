package com.yulece.weixing.dao;

import com.yulece.weixing.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderMasterDao
 * @Package com.yulece.weixing.dao
 * @Description: 订单持久层接口
 * @Date 2018/4/14/20:33
 **/
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    /*查询用户所有订单*/
    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);
    /*根据订单状态查询订单*/
    List<OrderMaster> findByOrderStatus(Integer status);
    /*根据订单支付状态查询订单*/
    List<OrderMaster> findByPayStatus(Integer status);
}
