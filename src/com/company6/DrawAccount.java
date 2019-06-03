package com.company6;

public class DrawAccount implements Runnable {
    Bank bank;
    public DrawAccount(Bank bank) {
        this.bank = bank;
    }
    @Override
    public void run() {
        while (true) {
            bank.drawAccount();
        }

    }
}

