package com.major.beauty.dao;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.major.base.log.LogUtil;
import com.major.beauty.bean.CostRecord;

import java.util.List;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.dao
 * ProjectName: Beauty
 * Date: 2019/10/28 10:47
 */
public class CostRecordDao extends BaseDao<CostRecord> {

    @Override
    public List<CostRecord> query() {
        QueryBuilder<CostRecord> where = new QueryBuilder<>(CostRecord.class).where("isDel=?", 0);
        LogUtil.w("statement " + where.createStatement().toString());
        return liteOrm.query(where);
    }
}
