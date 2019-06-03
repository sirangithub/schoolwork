package com.company5;
import java.util.Date;
public class Consumer implements Runnable {
    private String name;
    private Storehouse sh = null;

    public Consumer(String name, Storehouse sh) {
        this.name = name;
        this.sh = sh;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            Product product = sh.pop();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
