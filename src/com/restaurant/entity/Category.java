package com.restaurant.entity;

/**
 * @author zhangrong
 * 菜品分类实体类
 */
public class Category {
    private int id;  //菜品分类序号
    private String name;  //菜品分类名称
    private String describ;  //菜品描述
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
    public String getDescrib() {
        return describ;
    }
    public void setDescrib(String describ) {
        this.describ = describ;
    }
}
