package com.company3;
public class Test {
    public static void main(String [] args){
        Point<Integer,Integer> p1=new Point<Integer, Integer>();
        Point<Double,Double>p2=new Point<Double, Double>();
        Point<String,String>p3=new Point<String,String>();
        p1.printfCoordinate(1,1);
        System.out.print('\n');
        p2.printfCoordinate(1.2,1.2);
        System.out.print('\n');
        p3.printfCoordinate("一","二");
    }
}
