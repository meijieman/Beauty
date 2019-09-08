package com.major.beauty.dao;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.major.base.log.LogUtil;
import com.major.beauty.bean.Product;

import java.util.List;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/8 22:44
 */
public class ProductDao extends BaseDao<Product> {

    @Override
    public List<Product> query() {
        QueryBuilder<Product> where = new QueryBuilder<>(Product.class).where("isDel=?", 0);
        LogUtil.w("statement " + where.createStatement().toString());
        return liteOrm.query(where);
    }


}
