package com.company8;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class game1 {
    public static int a;
    public static char[] rnumber(){
        char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char []chs=new char[5];
        boolean[] flags=new boolean[letters.length];//默认值为false
        //随机选出字母
        /*int num =(int)(Math.random()*letters.length);
        for(int i=0;i<a.length;i++) {
            a[i]=letters[num];
        }*/
        int num;
        for(int i=0;i<chs.length;i++) {
            do {
                num = (int) (Math.random() * letters.length);
            } while (flags[num]);
            chs[i]=letters[num];
            flags[num]=true;
        }
        return chs;
    }
    public static boolean[] trueNum(char chs[],char input[]){
        boolean[] arr=new boolean[5];
        for(int i=0;i<input.length;i++){
           if(chs[i]==input[i]){
               arr[i]=true;
           }
        }
        return arr;
    }
    public static void main(String[] args){
       System.out.println("欢迎来到字母猜猜猜游戏");
       char[] chs=new char[5];
        chs= rnumber();
       //System.out.println(chs);
       Scanner scan=new Scanner(System.in);
       //获取控制台输入的字符
        System.out.println("游戏开始，请输入五个不同字母的序列(输入EXIT，退出游戏)：");
       String str=scan.next();
        char[] input =str.toUpperCase().toCharArray();
       String CHS=String.valueOf(chs);
       int count=1;
       //将用户输入的字符串转换成char数组类型
       while(!str.toUpperCase().equals(CHS)){
           if(str.toUpperCase().equals("EXIT")){
               System.out.println("游戏结束，最终得分为0");
               System.exit(0);
           }
           System.out.println(input);
           boolean []arr=trueNum(chs,input);
           System.out.println("对的位置："+ Arrays.toString(arr));
           System.out.println("请重新输入：");
           count++;
           str=scan.next();
           input =str.toUpperCase().toCharArray();
        }
        System.out.println("恭喜全部正确");
       System.out.println("花费次数："+count+"\n得分为："+(510-10*count));
       System.exit(0);
        /**
         * length和length()
         * 属性和方法
         */
    }
}
