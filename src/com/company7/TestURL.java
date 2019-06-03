package com.company7;

import com.sun.activation.registries.MailcapParseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TestURL {
    public static void main(String[] args){
        try {
            String s_url = "https://www.baidu.com/";
            URL url = new URL(s_url);
            System.out.println(url.getAuthority());
            System.out.println("端口号：" + url.getDefaultPort());
            System.out.println("协议名：" + url.getProtocol());
            System.out.println("主机名：" + url.getHost());
            System.out.println("路径：" + url.getPath());
        }catch (MalformedURLException e){
            e.getStackTrace();
        }
    }
}
