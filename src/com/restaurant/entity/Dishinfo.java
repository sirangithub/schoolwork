package com.restaurant.entity;

import java.io.Serializable;

public class Dishinfo implements Serializable {
    private int deskid;
    private int dishid;
    private double money;
    private int amonut;

    public int getDeskid() {
        return deskid;
    }

    public void setDeskid(int deskid) {
        this.deskid = deskid;
    }

    public int getDishid() {
        return dishid;
    }

    public void setDishid(int dishid) {
        this.dishid = dishid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getAmonut() {
        return amonut;
    }

    public void setAmonut(int amonut) {
        this.amonut = amonut;
    }
}
