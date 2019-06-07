package com.major.beauty.bean;

/**
 * @desc: 产品库存清单
 * @author: Major
 * @since: 2019/6/7 23:30
 */
public class ProductList {

    private String productName; // 对应 Product 的 name
    private int count; // 相同产品的数量

    private String productionDate; // 生产日期

    private String expiryDate; // 过期时间

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}