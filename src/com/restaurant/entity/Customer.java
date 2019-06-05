package com.restaurant.entity;

/**
 * @author zhangrong
 * 客户信息实体类
 */
public class Customer {
    private int id;  //客户序号
    private String name;  //客户用户名
    private String sex;  //客户性别
    private String company; //客户单位
    private String tel;  //客户电话
    private String cardID;  //贵宾卡号
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
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getCardID() {
        return cardID;
    }
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
}
