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
}
