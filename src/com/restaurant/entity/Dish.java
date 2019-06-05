package com.restaurant.entity;

import java.awt.*;
import java.io.Serializable;

/**
 * @author zhangrong
 * 菜品信息实体类
 */
public class Dish implements Serializable {
    private int id;  //菜品信息序号
    private String name;  //菜品名
    private int categoryId;  //菜品类别编号
    private Image pic;//图片
    private String code; //菜品代码
    private String unit;  //菜品单位
    private double price;  //菜品价格
    private String status;  //菜品状态
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
    public int getCategoryId() {
        return categoryId;
    }
    public Image getPic() {
        return pic;
    }
    public void setPic(Image pic) {
        this.pic = pic;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
