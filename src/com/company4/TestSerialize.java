package com.company4;

import java.io.*;
public class TestSerialize {
    public static void main(String[] args) {
        Address address = new Address("中国", "吉林", "长春");
        Employee employee = new Employee("张xx", 30, address);
        File file = new File("d:\\employee23.dat");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employee);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
