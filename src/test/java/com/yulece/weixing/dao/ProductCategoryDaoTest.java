package com.yulece.weixing.dao;

import com.yulece.weixing.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author wangyichao@28ph.cn
 * @Description: 完成对类目持久层测试
 * @date 2018/4/1121:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void  TestFindOne(){
        Optional<ProductCategory> productCategory = productCategoryDao.findById(1);
        System.out.print(productCategory.get());
    }

    @Test
    public void TestSave(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("我的最爱");
        productCategory.setCategoryType(2);
        productCategoryDao.save(productCategory);
    }

    @Test
    @Transactional
    public void TestUpdate(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(8);
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(1);
        productCategoryDao.save(productCategory);
        //Assert.
    }
}