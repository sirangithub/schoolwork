package com.company2;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class rProperties {


    public static void readProperties(){
        Properties prop=new Properties();
        InputStream inputStream=Object.class.getResourceAsStream("config.properties");
        InputStreamReader inputStreamReader=null;
        try {
            inputStreamReader=new InputStreamReader(inputStream,"GBK");
        }catch (IOException e){
            e.printStackTrace();
        }try {
            prop.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(prop.get("warshipType1"));
    }
}
