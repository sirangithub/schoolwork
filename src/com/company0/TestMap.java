package com.company0;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestMap {
    public Map<String,Student>student;
    public  TestMap(){
        this.student=new HashMap<String, Student>();
    }
    public void addStudent(String stuid,String stuName){
        Student stu=student.get(stuid);
        if(stu==null){
            Student newStu=new Student(stuid,stuName);
            student.put(stuid,newStu);
        }else{
            System.out.println("该学生的ID已被占用");
        }
    }
    public void removeStudent(String stuid){
        Student stu=student.get(stuid);
        if(stu==null){
            System.out.println("输入该学生的ID不存在");
        }
        else{
            student.remove(stuid);
            System.out.println("成功删除学生"+stu.getName());
        }
    }
    public void modifyStudent(String stuid,String stuName){
        Student stu=student.get(stuid);
        if(stu==null){
            System.out.println("输入该学生的ID不存在");
        }
        else{
            Student newStu=student.get(stuid);
            student.put(stuid,newStu);
        }
    }
    public void viewallStudent(){
        Set<Map.Entry<String,Student>>entrySet=student.entrySet();
        for(Map.Entry<String,Student>entry:entrySet){
            System.out.println("学生ID："+entry.getValue().getId()+" 学生姓名："+entry.getValue().getName());
        }
    }
}
