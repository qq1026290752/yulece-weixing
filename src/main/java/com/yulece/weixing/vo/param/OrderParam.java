package com.yulece.weixing.vo.param;

import javax.validation.constraints.NotNull;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderParam
 * @Package com.yulece.weixing.vo
 * @Description: 订单参数VO
 * @Date 2018/4/15/20:19
 **/
public class OrderParam {

    @NotNull(message = "请输入买家姓名")
    private String Name;
    @NotNull(message = "请输入买家联系方式")
    private String phone;
    @NotNull(message = "请输入买家送餐地址")
    private String address;
    @NotNull(message = "买家用户ID不能为空")
    private String openid;
    @NotNull(message = "购物车信息不能为空")
    private String items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
