package com.company8;

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
    public static void main(String[] args){
       int b=0+10;
       int count=0;
       System.out.println(b);
       System.out.println(rnumber());
       Scanner scan=new Scanner(System.in);
       //获取控制台输入的字符
        System.out.println("请输入要猜的字符：");
       String str=scan.next();
      // System.out.println(str);
       //char[] 类型？
        // 将用户输入的字符串转换成char数组类型
        char[] input =str.toCharArray();
       /* for(int i=0;i<rnumber().length;i++){
            if(input[i].equals(rnumber()[i])
            count++;
        }*/
        System.out.println(input);
        System.out.println(count);
        /**
         * length和length()
         * 属性和方法
         */
    }
}
