package com.yulece.weixing.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yulece.weixing.entity.OrderDetail;
import com.yulece.weixing.enums.OrderEnum;
import com.yulece.weixing.utils.serializer.DateToLongSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderDto
 * @Package com.yulece.weixing.dto
 * @Description: 订单传输类
 * @Date 2018/4/14/22:12
 **/
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderEnum.OrderStatusEnum.NEW.getCode();
    private Integer payStatus = OrderEnum.PayStatusEnum.WAIT.getCode();

    private List<OrderDetail> orderDetails;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
