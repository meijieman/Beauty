package com.major.beauty.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Desc: 顾客资料
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.bean
 * ProjectName: Beauty
 * Date: 2019/6/3 16:37
 */
public class Customer {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private long cid;

    // 基本资料
    @NotNull
    private String name; // 姓名
    @NotNull
    private String phone; // 手机号

    // 详细资料
    private String sex; // 性别
    private int height; // 身高（cm）
    private float weight; // 体重（kg）
    private String company; // 职业, 工作单位
    private int marrided; // 0 未婚，1已婚
    private String birthday;
    private String lunarBirthday; // 农历生日
    private String weddingDay; // 结婚纪念日

    private String skinType; // 皮肤类型
    private String nursingNeeds; // 调养需求
    private String availableTime; // 最佳联系时间

    private String comment; // 备注
    private String iconUrl; // 头像 url

    @NotNull
    private long createTime; // 记录创建时间
    private long modifyTime; // 最近修改时间
    @NotNull
    private String operator; // 最近操作员工

    public Customer(String operator){
        setCreateTime(System.currentTimeMillis());
        setOperator(operator);
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getMarrided() {
        return marrided;
    }

    public void setMarrided(int marrided) {
        this.marrided = marrided;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLunarBirthday() {
        return lunarBirthday;
    }

    public void setLunarBirthday(String lunarBirthday) {
        this.lunarBirthday = lunarBirthday;
    }

    public String getWeddingDay() {
        return weddingDay;
    }

    public void setWeddingDay(String weddingDay) {
        this.weddingDay = weddingDay;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public String getNursingNeeds() {
        return nursingNeeds;
    }

    public void setNursingNeeds(String nursingNeeds) {
        this.nursingNeeds = nursingNeeds;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight +
                ", company='" + company + '\'' +
                ", marrided=" + marrided +
                ", birthday='" + birthday + '\'' +
                ", lunarBirthday='" + lunarBirthday + '\'' +
                ", weddingDay='" + weddingDay + '\'' +
                ", skinType='" + skinType + '\'' +
                ", nursingNeeds='" + nursingNeeds + '\'' +
                ", availableTime='" + availableTime + '\'' +
                ", comment='" + comment + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
