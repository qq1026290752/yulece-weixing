package com.yulece.weixing.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yulece.weixing.entity.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Title: ProductVo
 * @Package com.yulece.weixing.vo
 * @Description:商品视图返回
 * @Date 2018/4/1415:48
 **/
public class ProductVo  {

    @JsonProperty(value = "name")
    private String categoryName;

    @JsonProperty(value = "type")
    private Integer categoryType;

    @JsonProperty(value = "foods")
    private List<ProductInfoVo> productInfoVos;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVo> getProductInfoVos() {
        return productInfoVos;
    }

    public void setProductInfoVos(List<ProductInfoVo> productInfoVos) {
        this.productInfoVos = productInfoVos;
    }
}
