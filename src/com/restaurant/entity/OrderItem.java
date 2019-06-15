package com.restaurant.entity;

import java.io.Serializable;

/**
 * @author zhangrong
 * 点菜信息实体类
 */

public class OrderItem implements Serializable {
    private int id;  //点菜序号
    private int orderId;  //订单编号（外键）
    private int dishId;  //菜品编号（外键）
    private double amount;  //菜品数量
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getDishId() {
        return dishId;
    }
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
