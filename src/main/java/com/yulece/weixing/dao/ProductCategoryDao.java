package com.yulece.weixing.dao;

import com.yulece.weixing.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Description: 完成类目表的持久层
 * @date 2018/4/1121:07
 **/
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);
}
