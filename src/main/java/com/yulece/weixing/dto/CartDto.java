package com.yulece.weixing.dto;

/**
 * @author wangyichao@28ph.cn
 * @Title: CartDto
 * @Package com.yulece.weixing.dto
 * @Description: 购物车Dto
 * @Date 2018/4/15/9:59
 **/
public class CartDto {

    private String productId;
    private Integer productQuantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
