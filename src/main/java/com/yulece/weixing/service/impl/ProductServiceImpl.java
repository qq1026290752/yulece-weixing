package com.yulece.weixing.service.impl;

import com.yulece.weixing.dao.ProductInfoDao;
import com.yulece.weixing.entity.ProductCategory;
import com.yulece.weixing.entity.ProductInfo;
import com.yulece.weixing.enums.ProductStatusEnum;
import com.yulece.weixing.service.ProductCategoryService;
import com.yulece.weixing.service.ProductService;
import com.yulece.weixing.vo.ProductInfoVo;
import com.yulece.weixing.vo.ProductVo;
import com.yulece.weixing.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangyichao@28ph.cn
 * @Description: 商品服务接口实现
 * @date 2018/4/1318:12
 **/
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public ProductInfo findById(String productId) {
        return productInfoDao.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findByProductStatusAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }


    @Override
    public void save(ProductInfo productInfo) {
        productInfoDao.save(productInfo);
    }

    @Override
    public ResultVo<List<ProductVo>> getProductAll() {
        //查询全部的商品
        List<ProductInfo> upProductInfoResult = findByProductStatusAll();
        List<Integer> collect = upProductInfoResult
                .stream().map(info -> info.getCategoryType())
                .distinct().collect(Collectors.toList());
        //查询全部的类目
        List<ProductCategory> productCategoryResult = productCategoryService.findByCategoryTypeIn(collect);
        //组装数据
        List<ProductVo> productVos = new ArrayList<>();
        for(ProductCategory productCategory :productCategoryResult){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for(ProductInfo productInfo: upProductInfoResult){
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
                productVo.setProductInfoVos(productInfoVos);
            }
            productVos.add(productVo);
        }

        return ResultVo.createSuccessResult("成功",  productVos);
    }
}
