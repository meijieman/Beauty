package com.major.beauty.bean;

import com.litesuits.orm.db.annotation.Table;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/10 23:08
 */
@Table("product_count")
public class ProductCount extends Base {

    private long pid; // Product 的 id
    private String productName; // Product 的 name
    private String productUnit; // Product 的 单位

    private int count; // 数量

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    @Override
    public String toString() {
        return "ProductCount{" +
                "pid=" + pid +
                ", productName=" + productName +
                ", productUnit='" + productUnit + '\'' +
                ", count=" + count +
                '}';
    }
}
