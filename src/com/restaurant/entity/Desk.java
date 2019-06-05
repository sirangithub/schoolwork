package com.restaurant.entity;

/**
 * @author zhangrong
 * 餐台信息实体类
 */
public class Desk {
    private int id;  //餐台序号
    private String no;  //餐台编号
    private int seating;  //座位数
    private String status;  //餐台状态  已预定，已就餐，已结账
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public int getSeating() {
        return seating;
    }
    public void setSeating(int seating) {
        this.seating = seating;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
