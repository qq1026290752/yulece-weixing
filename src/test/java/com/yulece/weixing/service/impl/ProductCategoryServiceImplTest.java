package com.yulece.weixing.service.impl;

import com.yulece.weixing.entity.ProductCategory;
import com.yulece.weixing.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangyichao@28ph.cn
 * @Description: 类目服务测试类
 * @date 2018/4/1223:21
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> categoryTypeList = Lists.newArrayList(1,4);
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        Assert.assertEquals(2,categoryTypeList.size());
    }

    @Test
    public void findOne() {
        ProductCategory result = productCategoryService.findOne(8);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<ProductCategory> resultAll = productCategoryService.findAll();
        Assert.assertNotNull(resultAll);
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("招牌美食");
        productCategory.setCategoryType(3);
        productCategoryService.save(productCategory);

    }
}