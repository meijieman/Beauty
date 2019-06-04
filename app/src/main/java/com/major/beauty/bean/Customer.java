package com.major.beauty.bean;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.bean
 * ProjectName: Beauty
 * Date: 2019/6/3 16:37
 */
public class Customer {

    private String name; // 姓名
    private String address; // 住址
    private String phone; // 偏好
    private String favorite; // 偏好
    private String comment; // 备注
    private String iconUrl; // 头像 url

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
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

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", favorite='" + favorite + '\'' +
                ", comment='" + comment + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
