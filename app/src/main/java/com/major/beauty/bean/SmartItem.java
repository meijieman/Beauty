package com.major.beauty.bean;

import java.util.List;

/**
 * Desc: md 自定义输入控件
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.bean
 * ProjectName: Beauty
 * Date: 2019/8/15 16:47
 */
public class SmartItem {

    public static final int ITEM_DEFAULT = 0;
    public static final int ITEM_EDIT = 1;
    public static final int ITEM_SPINNER = 2;
    public static final int ITEM_TIMMER = 3;
    public static final int ITEM_GROUP = 4;

    private int type;
    private String title; // 标题
    private String subTitle; // 二级标题
    private String error; // 录入错误显示
    private String data; // 单条数据
    private boolean isEditable; // 是否可编辑
    private List<String> spinnerShow;

    private List<SmartItem> datas; // TODO 子控件

    public SmartItem(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<SmartItem> getDatas() {
        return datas;
    }

    public void setDatas(List<SmartItem> datas) {
        this.datas = datas;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public List<String> getSpinnerShow() {
        return spinnerShow;
    }

    public void setSpinnerShow(List<String> spinnerShow) {
        this.spinnerShow = spinnerShow;
    }
}
