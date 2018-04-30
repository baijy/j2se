package com.jianyu.thread.volatileDemo;

import java.util.concurrent.TimeUnit;

public class VolatileTest implements Runnable {
    private volatile int value = 100;

    public void run() {
        for(int i =0;i<100;i++){
            /*try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(Thread.currentThread().getName()+"----"+value);
            value --;

        }
    }

    public static void main(String[] args) {
        VolatileTest v1 = new VolatileTest();
        for(int i=0;i<11;i++){
            new Thread(v1).start();
        }
    }
}
