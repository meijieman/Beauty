package com.major.beauty.dao;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.major.base.log.LogUtil;
import com.major.beauty.bean.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/8 22:44
 */
public class ItemDao extends BaseDao<Item> {

    @Override
    public List<Item> query() {
        QueryBuilder<Item> where = new QueryBuilder<>(Item.class).where("isDel=?", 0);
        LogUtil.w("statement " + where.createStatement().toString());
        ArrayList<Item> query = liteOrm.query(where);
        if (query != null) {
            for (Item item : query) {
                // TODO: 2019/9/9 连表查询
//                item.setProductCounts();
            }
        }
        return query;
    }


    public long delById(long id) {
        Item item = liteOrm.queryById(id, Item.class);
        if (item != null) {
            // 软删除
            item.setDel(1);
            return liteOrm.update(item);
        }

        return -1;
    }
}
