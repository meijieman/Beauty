package com.major.beauty.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

/**
 * @desc: 产品
 * @author: Major
 * @since: 2019/6/7 23:20
 */
@Table("product")
public class Product extends Base {

    @NotNull
    private String name; // 产品名称
    private String instruction; // 说明
    private double price;
    private String comment;
    private String unit; // 单位
    private int isDel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setDel(int isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", instruction='" + instruction + '\'' +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", unit='" + unit + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}
