package com.company10;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args){
        /**
         * 假设：要计算不同的图形，谁的面积最大
         * 1.圆形：计算面积公式，周长*周长*0.75
         * 2.方形：计算面积公式，周长*周长*0.70
         * 需求：给定3个圆形的周长和方形的周长
         * 比较出圆形和方形，谁的面积最大
         * 例如：三个圆形：10,11,12
         *       三个方形：12,13,14
         *
         * 分析：
         * 1.碰到有相同属性或相同行为的情况，要专门把
         * 这些共同的东西提取出来，做成一个抽象类或者接口
         * 好处：可以减少重复代码
         * 2.在对应的子类中继承父类，重写方法，按照自己特有的
         * 行为，添加内容（添加方法体）
         */

        /**
         * 什么是构造方法：方法名和类名一样的方法，及叫做构造
         * 方法
         * new FangXing();实际上在调用FangXing类里面的
         * 无参构造
         * 构造方法的特点：
         * 1.如果类里面没有写构造方法，那么，默认的会有一个
         * 隐藏的无参的构造方法
         * 2.构造方法没有返回值类型，同时，也不能写void
         * 3.如果我们添加了构造方法，那么系统就不会给我们
         * 创建默认的无参构造了，这时候，需要我们自己创建
         * 一个无参构造
         */
        TuXing[] area=new TuXing[9];
        area[0]=new YuanXing(21.2);
        area[1]=new YuanXing(21.3);
        area[2]=new YuanXing(21.3);
        area[3]=new FangXing(20.0);
        area[4]=new FangXing(20.2);
        area[5]=new FangXing(19.9);
        area[6]=new DuoBianXing(19.8);
        area[7]=new DuoBianXing(19.7);
        area[8]=new DuoBianXing(20.2);
        for(int i=0;i<area.length;i++){
            for(int j=i;j<area.length;j++){
                if(area[i].area()<area[j].area()){
                    TuXing t=area[i];
                    area[i]=area[j];
                    area[j]=t;
                }
            }
        }
        System.out.println(area[0]);
    }
}
