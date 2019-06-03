package com.company4;
import java.io.*;
public class TestDeserialize {
    public static void main(String[] args){
        File file =new File("d:\\employee23.dat");
        Employee employee1=null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            employee1 = (Employee) ois.readObject();
            System.out.println("修改前员工的信息：");
            System.out.println(employee1);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        employee1.getAddress().setState("中国");
        employee1.getAddress().setProvince("湖北省");
        employee1.getAddress().setCity("武汉市");
        try {
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(employee1);
            oos.flush();
            oos.close();
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        Employee employee2=null;
        try {
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            employee2=(Employee)ois.readObject();
            System.out.println("修改后的员工的信息：");
            System.out.println(employee2);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
