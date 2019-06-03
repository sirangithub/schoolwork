package com.company2;
public class Point {
    public int x;
    private int y;
    private String name;//该点的名称
    /**
     * 无参的构造函数
     */
    public Point(){

    }

    /**
     * 一个参数的构造函数
     * @param name
     */
    public Point(String name){
        this.name=name;
    }

    /**
     *
     * 1个参数的构造函数
     * @param  x
     */
    public Point(int x){
        this.x=x;
    }

    /**
     * 2个参数的故障函数
     * @param x
     * @param y
     */
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    /**
     * 3个参数的构造函数
     * @param x
     * @param y
     * @param name
     */
    public Point(int x, int y, String name){
        this.x=x;
        this.y=y;
        this.name=name;
    }

    /**
     * 移动点到新的位置，水平位移量dx,垂直位移量dy，并命名新的名称
     * @param dx
     * @param dy
     * @param nameString
     */
    public void move(int dx,int dy,String nameString){
        x=x+dx;
        y=y+dy;
        name=name+nameString;
    }
    public int hashCode(){
        final int prime=31;
        int result=1;
        result=prime*result+x;
        result=prime*result+y;
        return  result;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x=x;
    }
    public int getY(){
        return y;
    }
    public void setY(){
        this.y=y;
    }
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        if(this==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        Point other=(Point) obj;
        if(x!=other.x)
            return false;
        if(y!=other.y)
            return false;
        return true;
    }
}
