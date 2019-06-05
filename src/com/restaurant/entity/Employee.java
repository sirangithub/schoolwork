package com.restaurant.entity;


import java.util.Date;

/**
 * @author zhangrong
 * 员工信息实体类
 */
public class Employee {
    private int id; //员工序号
    private String name;  //员工用户名
    private String sex;  //员工性别
    private Date birthday;  //员工出生日期
    private String identityID;  //员工身份证号
    private String address;  //员工家庭住址
    private String tel;  //员工电话
    private String position;  //员工职位
    private String freeze; //是否在值
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getIdentityID() {
        return identityID;
    }
    public void setIdentityID(String identityID) {
        this.identityID = identityID;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getFreeze() {
        return freeze;
    }
    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }
}
