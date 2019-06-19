package com.restaurant.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report implements Serializable {
    private int id;
    private String deskno;
    private String dishname;
    private double price;
    private int amount;
    private Timestamp otime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskno() {
        return deskno;
    }

    public void setDeskno(String deskno) {
        this.deskno = deskno;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getOtime() {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ostime=df.format(otime);
        return ostime;
    }

    public void setOtime(String ottime) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ottime=df.format(new Date());
        otime=Timestamp.valueOf(ottime);
    }
}
