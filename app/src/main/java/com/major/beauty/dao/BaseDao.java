package com.major.beauty.dao;

import com.litesuits.orm.LiteOrm;
import com.major.beauty.base.App;
import com.major.beauty.bean.Base;

import java.util.List;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/8 19:48
 */
public abstract class BaseDao<T extends Base> {

    protected static LiteOrm liteOrm = App.getInstance().getLiteOrm();

    public abstract List<T> query();

    public long queryCount() {
        List<T> query = query();
        return query == null ? 0 : query.size();
    }

    public T queryById(long id, Class<T> clazz) {
        return liteOrm.queryById(id, clazz);
    }

    public long insertOrUpdate(T t) {
        return liteOrm.save(t);
    }
}
