package com.restaurant.util;

/**
 * @author zhangrong
 * 变动类
 */

public class Changed {
    private int id;  //发生变动的序号
    private int col;  //发生变动的位置
    private String value;  //发生变动后的值
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return id+","+col+","+value;
    }
}
