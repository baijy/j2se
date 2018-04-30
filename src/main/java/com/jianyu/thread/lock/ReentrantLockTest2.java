package com.jianyu.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示tryLock的使用
 * 获取不到锁的时候，返回false
 *
 */
public class ReentrantLockTest2 {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReentrantLockTest2 test = new ReentrantLockTest2();

        for (int i = 0; i < 10; i++) {
            new Thread("A"+i){
                public void run(){
                    test.insert(Thread.currentThread()); // 获取当前线程
                };
            }.start();
        }

    }


    public void insert (Thread thread){
        if( lock.tryLock()){
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
        }else{
            System.out.println("线程："+thread.getName()+"获取锁失败...");
        }


    }

}
