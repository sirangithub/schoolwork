package com.restaurant.util;
public class Changed {
    private int id;  
    private int col;  
    private String value;  
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
    public String toString() {
        return id+","+col+","+value;
    }
}
