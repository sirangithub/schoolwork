package com.company1;
public class Test {
    public static void main(String[] args){
        Teacher t=new Teacher();
        t.addTeaher("张和","Java语言");
        t.addTeaher("李纪","C语言");
        t.addTeaher("王桔","JSP");
        t.addTeaher("胡海","Oracle数据库");
        t.addTeaher("刘山","网页设计");
        t.addTeaher("郭富","JSP");
        t.addTeaher("付格","Linux");
        t.addTeaher("Lucy","Java语言");
        t.viewallTeachers();
        t.addTeaher("Allen","JDBC");
        t.modifyTeacher("Lucy","CoreJava");
        t.viewallTeachers();
        t.viewTeachers("JSP");
    }
}
