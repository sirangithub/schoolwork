package com.restaurant.entity;

/**
 * @author zhangrong
 * 管理员信息实体类
 */
public class User {
    private int id;  // 管理员序号
    private String username;  //管理员登录账户
    private String password;  //管理员登录密码
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id+","+username+","+password;
    }
}
