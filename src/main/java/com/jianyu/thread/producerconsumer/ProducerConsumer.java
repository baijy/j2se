package com.jianyu.thread.producerconsumer;

public class ProducerConsumer {
    public static void main(String[] args) {
        //持有同一个资源
        Bread bread = new Bread();
        //创建两个任务
        Producer pro = new Producer(bread);
        Consumer con = new Consumer(bread);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(pro);
        Thread t3 = new Thread(con);
        Thread t4 = new Thread(con);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
