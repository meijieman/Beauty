package com.major.beauty.dao;

import com.litesuits.orm.LiteOrm;
import com.major.beauty.base.App;
import com.major.beauty.bean.Customer;

/**
 * @desc: 顾客数据库操作
 * @author: Major
 * @since: 2019/9/8 16:31
 */
public class CustomerDao {

    private static LiteOrm liteOrm = App.getInstance().getLiteOrm();

    public static long insertOrUpdate(Customer c) {
        Customer customer = liteOrm.queryById(c.getCid(), Customer.class);
        if (customer == null) {
            return liteOrm.insert(c);
        } else {
            return liteOrm.update(c);
        }
    }
}
