package com.company0;

public class Test {
    public static void main(String[] args){
        TestMap mt=new TestMap();
        mt.addStudent("001","张三");
        mt.addStudent("002","李四");
        mt.addStudent("003","王五");
        mt.addStudent("001","张三");
        mt.viewallStudent();
        mt.removeStudent("002");
        mt.removeStudent("002");
        mt.removeStudent("005");
        mt.viewallStudent();
        mt.modifyStudent("002","李六");
        mt.modifyStudent("003","老王");
        mt.viewallStudent();
    }
}
