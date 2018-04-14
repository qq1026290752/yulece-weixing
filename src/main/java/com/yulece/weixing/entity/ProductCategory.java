package com.yulece.weixing.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author wangyichao@28ph.cn
 * @date 2018/4/1120:59
 **/
@Entity
@DynamicUpdate
public class ProductCategory implements Example {

    //类目ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    //类目名称
    private String categoryName;
    //类目类型
    private Integer categoryType;
    //创建时间
    private Date createTime;

    //更新日期
    private Date updateTime;

    @Override
    public ProductCategory getProbe() {
        return new ProductCategory();
    }

    @Override
    public ExampleMatcher getMatcher() {
        return null;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

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
}
