package com.company3;
class Point<X,Y> {
     public X x;
     public Y y;
     /**public Point(X x,Y y){
         this.x=x;
         this.y=y;
     }*/
     public void printfCoordinate(X x,Y y) {
         System.out.print(x);
         System.out.print(',');
         System.out.print(y);
     }
 }
