package com.major.beauty.dao;

import com.major.beauty.bean.Appointment;

import java.util.List;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/11/2 8:46
 */
public class ApptDao extends BaseDao<Appointment> {

    @Override
    public List<Appointment> query() {
        return liteOrm.query(Appointment.class);
    }
}
