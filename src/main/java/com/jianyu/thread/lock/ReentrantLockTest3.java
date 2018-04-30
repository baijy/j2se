package com.jianyu.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示带参数的tryLock() 对获取锁的中断
 * 尝试一定时间的获取锁，超时以后中断
 */
public class ReentrantLockTest3 {

    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        ReentrantLockTest3 test = new ReentrantLockTest3();
        MyThread thread1 = new MyThread(test,"A");
        MyThread thread2 = new MyThread(test,"B");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException{
        // 如果4秒得不到锁，则中断，抛出InterruptedException异常
        // B线程进来的时候，A线程还没结束运行
        if(lock.tryLock(4, TimeUnit.SECONDS)){
            try {
                System.out.println("time=" + System.currentTimeMillis() + " ,线程 " + thread.getName()+"得到了锁...");
                long now = System.currentTimeMillis();
                while (System.currentTimeMillis() - now < 4100) {
                    // 为了避免Thread.sleep()而需要捕获InterruptedException而带来的理解上的困惑,
                    // 此处用这种方法空转5秒
                }
            }finally{
                lock.unlock();
            }
        }else {
            System.out.println("线程 " + thread.getName()+"放弃了对锁的获取...");
        }
    }

}

class MyThread extends Thread{
    private ReentrantLockTest3 test = null;

    public MyThread(ReentrantLockTest3 test,String name) {
        super(name);
        this.test = test;
    }

    @Override
    public void run() {
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println("time=" + System.currentTimeMillis() + " ,线程 " + Thread.currentThread().getName() + "被中断...");
        }
    }
}