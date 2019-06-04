package com.company10;

public class YuanXing extends TuXing {
    public YuanXing() {
    }
    public YuanXing(double c){
        this.c=c;
    }

    @Override
    public double area() {
        return c*c*0.75;
    }
    @Override
    public String toString() {
        return "最大面积的为原始周长为"+c+"的圆形，面积为"+area();
    }
}
