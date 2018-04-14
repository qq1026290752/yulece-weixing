package com.yulece.weixing.service.impl;

import com.yulece.weixing.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wangyichao@28ph.cn
 * @Description: 商品服务测试
 * @date 2018/4/1319:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findByProductStatusAll() {
        List<ProductInfo> resultList = productService.findByProductStatusAll();
        Assert.assertEquals(1,resultList.size());
    }

    @Test  
    public void findByAll(){
        PageRequest request = PageRequest.of(0, 10);
        Page<ProductInfo> result = productService.findAll(request);
        Assert.assertEquals(1L,result.getTotalElements());
    }
}