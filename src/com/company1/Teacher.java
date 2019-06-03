package com.company1;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Teacher {
   private Map<String,String>Teacher;
   public Teacher(){
       this.Teacher=new HashMap<String, String>();
   }
   public void addTeaher(String Tname,String Course){
       if(Teacher.containsKey(Tname)){
           System.out.println("已存在该老师信息");
       }else {
           Teacher.put(Tname,Course);
           System.out.println("添加成功，新老师的信息为： 姓名 "+Tname+"课程 "+Course);
       }
   }
   public void modifyTeacher(String Tname,String Course){
       if(Teacher.containsKey(Tname)){
           Teacher.put(Tname,Course);
           System.out.println("修改成功，修改后老师信息为： 姓名 "+Tname+" 课程："+Course);
       }else {
           System.out.println("不存在该老师信息");
       }
   }
   public void viewallTeachers(){
       Set<Map.Entry<String,String>>entrySet=Teacher.entrySet();
       for(Map.Entry<String,String>entry:entrySet){
           System.out.println("老师姓名："+entry.getKey()+" 课程:"+entry.getValue());
       }
   }
   public void viewTeachers(String Course){
       Set<Map.Entry<String,String>>entrySet=Teacher.entrySet();
       System.out.println("教"+Course+"的老师：");
       for(Map.Entry<String,String>entry:entrySet){
           if(entry.getValue().equals(Course)){
               System.out.print(entry.getKey()+"\t");

           }
       }
   }
}
