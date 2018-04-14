package com.yulece.weixing.service.impl;

import com.yulece.weixing.dao.ProductCategoryDao;
import com.yulece.weixing.entity.ProductCategory;
import com.yulece.weixing.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Description: 类目实现service接口
 * @date 2018/4/1223:06
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypes);
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
