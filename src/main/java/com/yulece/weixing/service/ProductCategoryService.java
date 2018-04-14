package com.yulece.weixing.service;

import com.yulece.weixing.entity.ProductCategory;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Description:类目service提供接口
 * @date 2018/4/1222:31
 **/
public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    ProductCategory save(ProductCategory productCategory);
}
