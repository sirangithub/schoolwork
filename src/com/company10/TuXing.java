package com.company10;

/**
 * @author PC
 * 做一个圆形的类：将所有其他不同图形的属性
 * 提取出来
 * 抽象类的特点：
 * 1.抽象类也是不能直接被实例化的，但是，可以创建数组的对象
 * 2.抽象类中不一定有抽象方法，但是，有抽象方法的类一定是
 * 抽象类
 * 3.抽象类一定是用来被继承的，不然存在没有意义
 */
public abstract class TuXing {
    //提出公共的属性和行为
    double c;
    public abstract double area();
}
