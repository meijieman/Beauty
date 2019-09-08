package com.major.beauty.dao;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;
import com.major.beauty.bean.Customer;

import java.util.List;

/**
 * @desc: 顾客数据库操作
 * @author: Major
 * @since: 2019/9/8 16:31
 */
public class CustomerDao extends BaseDao<Customer> {

    public List<Customer> query() {
        QueryBuilder<Customer> where = new QueryBuilder<>(Customer.class).where("isDel=?", 0);
//        LogUtil.w("statement " + where.createStatement().toString());
        return liteOrm.query(where);
    }

    public long delById(long cid) {
        Customer customer = liteOrm.queryById(cid, Customer.class);
        if (customer != null) {
            // 软删除
            customer.setDel(1);
            return liteOrm.update(customer);
        }

        return -1;
    }

    public long insertOrUpdate(Customer c) {
        Customer customer = liteOrm.queryById(c.getCid(), Customer.class);
        if (customer == null) {
            return liteOrm.insert(c, ConflictAlgorithm.Fail);
        } else {
            return liteOrm.update(c, ConflictAlgorithm.Fail);
        }
    }
}
