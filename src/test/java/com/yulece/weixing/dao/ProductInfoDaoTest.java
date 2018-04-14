package com.yulece.weixing.dao;

import com.yulece.weixing.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author wangyichao@28ph.cn
 * @Description: 商品详情测试
 * @date 2018/4/1317:35
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = productInfoDao.findByProductStatus(0);
        Assert.assertEquals(1,result.size());
    }

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString().replaceAll("-",""));
        productInfo.setProductName("汉堡");
        productInfo.setCategoryType(4);
        productInfo.setProductStatus(0);
        productInfo.setProductStock(10);
        productInfo.setProductPrice(new BigDecimal(20.5));
        productInfo.setProductDescription("群主最爱吃的垃圾食品");
        productInfo.setProductIcon("https://alida.cn/xxx.jpg");
        ProductInfo result = productInfoDao.save(productInfo);
        Assert.assertNotNull(result);
    }
}