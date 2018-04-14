package com.yulece.weixing.dao;

import com.yulece.weixing.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Description: 商品详情持久层接口
 * @date 2018/4/1317:23
 **/
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
