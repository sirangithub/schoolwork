package com.company8;

public class game1 {
    public static char[] rnumber(){
        char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char []a=new char[5];
        boolean[] flags=new boolean[letters.length];//默认值为false
        //随机选出字母
        /*int num =(int)(Math.random()*letters.length);
        for(int i=0;i<a.length;i++) {
            a[i]=letters[num];
        }*/
        int num;
        for(int i=0;i<a.length;i++) {
            do {
                num = (int) (Math.random() * letters.length);
            } while (flags[num]);
            a[i]=letters[num];
            flags[num]=true;
        }
        return a;
    }
    public static void main(String[] args){
       int b=0+10;
       System.out.println(b);
       System.out.println(rnumber());
    }
}
