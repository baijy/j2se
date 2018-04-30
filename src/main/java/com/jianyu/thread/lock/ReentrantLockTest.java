package com.jianyu.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示ReentrantLock的使用
 *
 */
public class ReentrantLockTest {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();

        for (int i = 0; i < 10; i++) {
            new Thread("A"+i){
                public void run(){
                    test.insert(Thread.currentThread()); // 获取当前线程
                };
            }.start();
        }

    }


    public void insert (Thread thread){
        lock.lock();

        try {
            System.out.println("线程"+thread.getName()+"得到了锁...");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程："+thread.getName()+"释放了锁...");
            lock.unlock();
        }
    }

}
