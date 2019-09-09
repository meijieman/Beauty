package com.major.beauty.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

import java.util.List;

/**
 * @desc: 项目
 * @author: Major
 * @since: 2019/6/7 23:20
 */
@Table("item")
public class Item extends Base {

    @NotNull
    private String name; // 项目名称
    private List<ProductCount> productCounts; // 包含的产品及其数量

    private long createTime; // 创建时间
    private String operator; // 最后操作者
    private int isDel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCount> getProductCounts() {
        return productCounts;
    }

    public void setProductCounts(List<ProductCount> productCounts) {
        this.productCounts = productCounts;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setDel(int isDel) {
        this.isDel = isDel;
    }

    public static class ProductCount {
        private Product product; // 产品
        private int count; // 数量

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
