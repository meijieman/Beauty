package com.major.beauty.bean;

/**
 * @desc: app 用户角色
 * @author: Major
 * @since: 2019/9/8 17:19
 */
public class Avatar {
    private static final int NO_GRADE = -1;

    private String name; // 名称
    private String password; // 登录密码
    private int grade = NO_GRADE; // 级别
    private long createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
