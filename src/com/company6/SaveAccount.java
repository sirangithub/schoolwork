package com.company6;

public class SaveAccount implements Runnable {
    Bank bank;
    public SaveAccount(Bank bank) {
        this.bank = bank;
    }
    @Override
    public void run() {
        while (true){
        bank.saveAccount();
        }
    }
}
