package com.major.beauty.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/8 22:10
 */
public class Base {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private long _id;

    public void setId(long id) {
        this._id = id;
    }

    public long getId() {
        return _id;
    }
}
