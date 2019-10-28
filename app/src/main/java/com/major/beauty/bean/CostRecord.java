package com.major.beauty.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.List;

/**
 * Desc: 消费记录
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.bean
 * ProjectName: Beauty
 * Date: 2019/10/28 10:12
 */
@Table("cost_record")
public class CostRecord extends Base {

    @NotNull
    private long createTime; // 消费日期

    @NotNull
    private long cid; // 顾客 id
    private String name; // 顾客姓名
    private String phone; // 顾客手机号

    private List<Product> products; // 产品
    private List<Item> items; // 项目

    private int duration; // 耗时，单位分钟

    @NotNull
    private double pay; // 消费总金额

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "CostRecord{" +
                "createTime=" + createTime +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", products=" + products +
                ", items=" + items +
                ", duration=" + duration +
                ", pay=" + pay +
                '}';
    }
}
