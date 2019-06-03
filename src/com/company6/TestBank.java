package com.company6;

public class TestBank {
    public static void main(String[] args){
        Bank bank1=new Bank("10001",10000);
        Bank bank2=new Bank("10002",20000);
        SaveAccount sa1=new SaveAccount(bank1);
        DrawAccount dr1=new DrawAccount(bank1);
        SaveAccount sa2=new SaveAccount(bank2);
        DrawAccount dr2=new DrawAccount(bank2);
        Thread ts1=new Thread(sa1);
        Thread td1=new Thread(dr1);
        Thread ts2=new Thread(sa2);
        Thread td2=new Thread(dr2);

        ts1.start();
        td1.start();
        ts2.start();
        td2.start();
    }
}
