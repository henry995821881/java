package com.henry.ssm.po;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Items {
    private Integer id;

    /**
     * 非空校验
     */
    @NotNull(message="{items.createtime.isnull}")
    private Date createTime;

    /**
     * 校验名称1-30字符
     *message 提示信息
     */
    @Size(min=1,max=30,message="{items.itemName.length.error}")
    private String itemName;

    private Float itemPrice;

    private String itemDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail == null ? null : itemDetail.trim();
    }
}