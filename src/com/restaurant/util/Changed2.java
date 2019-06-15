package com.restaurant.util;

public class Changed2 {
    private String obid;  //发生变动的对象
    private int col;  //发生变动的位置

    public String getObid() {
        return obid;
    }

    public void setObid(String obid) {
        this.obid = obid;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    @Override
    public String toString() {
        return obid+","+col+"\n";
    }
}
