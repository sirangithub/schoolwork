package com.company6;

public class Bank {
    private String account;
    private double balance;
    public Bank(String account,double balance){
        this.account=account;
        this.balance=balance;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "账户:"+account+"余额:"+balance;
    }
    public synchronized  void saveAccount(){
        double balance=getBalance();
        double balance1=Math.random()*10000;
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        balance=balance+balance1;
        setBalance(balance);
        System.out.println(getAccount()+"存入："+balance1+"账户余额:"+balance);
    }
    public synchronized void drawAccount(){
        double balance=getBalance();
        double balance2=Math.random()*10000;
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }if(balance2<balance){
        balance=balance-balance2;
        setBalance(balance);
        System.out.println(getAccount()+"取出："+balance2+"账户余额:"+balance);
        }else {System.out.println(getAccount()+"余额不足");}
    }
}
