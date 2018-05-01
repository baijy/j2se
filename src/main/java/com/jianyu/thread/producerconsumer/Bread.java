package com.jianyu.thread.producerconsumer;

/*
 * 需求：实现生产者-消费者模式，可以有多个生产者和多个消费者
 * 程序关键点：
 * 1、解决重复生产、重复消费的问题，需要使用synchronized（通过资源对象）进行同步
 * 2、生产、消费方法放在资源类中定义
 * 3、解决未生产先消费、已消费未生产的问题，实现生产一个消费一个，需要线程间通信并使用标志进行判断
 * 4、notify可能产生死锁，notifyAll可以唤醒其他所有线程
 * 5、wait()和notifyAll()省略了this
 *
 * **/
class Bread{
    private String name;
    public static int count = 1;
    private boolean flag =false;//是否有面包

    // synchronized必须放在void前面
    public synchronized void add(String name) {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.name = name +":"+count;
        System.out.println(Thread.currentThread().getName() + "..." + "生产：" + this.name);
        count++;
        flag = true;
        notifyAll();
    }

    public synchronized void minus() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println(Thread.currentThread().getName() + "..." + "消费：" + this.name);
        flag = false;
        notifyAll();
    }
}

//生产者
class Producer implements Runnable {
    private Bread bread = new Bread();
    Producer(Bread bread){
        this.bread = bread;
    }

    public void run()  {
        try {
            //这里不能少了while
            while(true){
                bread.add("面包");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//消费者
class Consumer implements Runnable {
    //定义成员变量、构造函数
    private Bread bread = new Bread();
    Consumer(Bread bread){
        this.bread = bread;
    }

    public void run() {
        try {
            //这里不能少了while
            while(true){
                bread.minus();
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
