package com.company10;

public class DuoBianXing extends TuXing {
    public DuoBianXing(double c){
        this.c=c;
    }
    @Override
    public double area() {
        return c*c*0.77;
    }

    @Override
    public String toString() {
        return "最大面积的为原始周长为"+c+"的多边形，面积为"+area();
    }
}
