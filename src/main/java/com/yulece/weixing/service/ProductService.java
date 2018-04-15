package com.yulece.weixing.service;

import com.yulece.weixing.dto.CartDto;
import com.yulece.weixing.entity.ProductInfo;
import com.yulece.weixing.vo.ProductVo;
import com.yulece.weixing.vo.ResultVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Description: 商品服务接口
 * @date 2018/4/1318:07
 **/
public interface ProductService {

    ProductInfo findById(String productId);

    /**
     * 根据商品状态查询商品
     * @return
     */
    List<ProductInfo> findByProductStatusAll();

    Page<ProductInfo> findAll(Pageable pageable);

    void save(ProductInfo productInfo);

    //获取类目下的商品集合

    ResultVo<List<ProductVo>> getProductAll();

    //增加库存
    void increaseStock(List<CartDto> cartDto);

    //减少库存
    void decreaseStock(List<CartDto> cartDto);
}
