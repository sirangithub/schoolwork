package com.company5;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Storehouse {
    int index=0;
    Product []pds=new Product[10];
    public synchronized void push(Product pd){
        while (index==pds.length){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        pds[index]=pd;
        System.out.println(Thread.currentThread().getName()+"生产了产品"+"["+pd.toString()+"]");
        this.index++;
        this.notify();
    }
    public synchronized Product pop(){
        while(index==0){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.index--;
        System.out.println(Thread.currentThread().getName()+"消费了产品"+"["+pds[index].toString()+"]");
        this.notify();
        return pds[index];
    }
}
