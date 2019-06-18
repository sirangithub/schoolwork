package com.restaurant.entity;

import java.awt.*;
import java.io.Serializable;

/**
 * @author zhangrong
 * 菜品信息实体类
 */
public class Dish implements Serializable{
    int id;
    private String name;
    private String code;
    private String unit;
    double price;
    private String status;
    private Category category;
    private String pic;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code=code;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit=unit;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price=price;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status=status;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category=category;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic=pic;
    }
}