package com.major.beauty.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.Table;

/**
 * @desc: 预约 appointment 英文缩写 appt
 * @author: Major
 * @since: 2019/11/2 8:42
 */
@Table("appointment")
public class Appointment extends Base {

    @NotNull
    private long cid; // 顾客id

    /**
     * {@link Customer#name}
     */
    @NotNull
    private String name; // 顾客姓名

    /**
     * {@link Customer#phone}
     */
    private String phone; // 手机号

    private long startTime; // 开始时间
    private long endTime; // 结束时间

    private long createTime; // 预约创建时间

    private String comment; // 备注

    private int expire; // 是否过期: 0 未过期， 1 已完成， 2 取消预约， 3 已过期

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", comment='" + comment + '\'' +
                ", expire=" + expire +
                '}';
    }
}
