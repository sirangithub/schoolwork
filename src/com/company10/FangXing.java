package com.company10;

public class FangXing extends TuXing {
    public FangXing(){
        System.out.println("无参构造方法");
    }
    public FangXing(double c){
        /**
         * 给周长变量赋值
         * 如果成员变量和局部变量的名字一样，那么，在调用
         * 成员变量的时候，一定要加this
         *this:调用成员变量
         *     调用构造方法
         *     调用成员方法
         *this:一般指的是当前对象（本类的对象）
         */
        this.c=c;
    }
    @Override
    public double area() {
        return c*c*0.70;
    }

    @Override
    public String toString() {
        return "最大面积的为原始周长为"+c+"的方形,面积为："+area();
    }
}
