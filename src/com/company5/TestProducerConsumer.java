package com.company5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestProducerConsumer {
    public static void main(String[] args){
        Storehouse sh=new Storehouse();
        Producer p1=new Producer("张三",sh);
        Producer p2=new Producer("李四",sh);
        Consumer p3=new Consumer("王五",sh);
        Consumer p4=new Consumer("老刘",sh);
        Thread tp1=new Thread(p1);
        Thread tp2=new Thread(p2);
        Thread tc1=new Thread(p3);
        Thread tc2=new Thread(p4);
        tp1.start();
        tp2.start();
        tc1.start();
        tc2.start();
        ExecutorService service= Executors.newCachedThreadPool();
        service.submit(tc1);
        service.submit(tc2);
        service.submit(tp1);
        service.submit(tp2);
    }
}
