package com.restaurant.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangrong
 * 开台信息实体类
 */
public class Order implements Serializable {
    private int id;  //订单序号
    private String orderNo;  //订单编号（自动生成，由当前日期+4位随机数）
    private int deskId;  //餐台号（外键）
    private String createtime;  //就餐日期时间
    private double money;  //金额
    private int customerId;  //客户编号
    private String status;  //订单状态：已支付，未支付
    private int number;  //就餐人数
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public int getDeskId() {
        return deskId;
    }
    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /*public String getCreatetime() {
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createtimes=df.format(createtime);
            return createtimes;
        }
        public void setCreatetime(String createtimet) {
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            createtimet=df.format(new Date());
            createtime=Timestamp.valueOf(createtimet);
        }*/
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
