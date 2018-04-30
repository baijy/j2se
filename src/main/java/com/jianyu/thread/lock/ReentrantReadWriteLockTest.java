package com.jianyu.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示 ReentrantReadWriteLock 的使用
 */
public class ReentrantReadWriteLockTest {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        new Thread("A") {
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

        new Thread("B") {
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
    }


    //如果有一个线程已经占用了【读】锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。
    //如果有一个线程已经占用了【写】锁，则此时其他线程如果申请写锁或者读锁，则申请的线程也会一直等待释放写锁
    // 只有读锁，可以同时获得
    public void get(Thread thread) {
        rwl.readLock().lock(); // 在外面获取锁
        try {
            long start = System.currentTimeMillis();
            System.out.println("线程" + thread.getName() + "开始读操作...");
            /*while (System.currentTimeMillis() - start <= 100) {
                System.out.println("线程" + thread.getName() + "正在进行读操作...");
            }*/
            for (int i = 0; i < 20; i++) {
                System.out.println("线程" + thread.getName() + "正在进行读操作...");
            }
            System.out.println("线程" + thread.getName() + "读操作完毕...");
        } finally {
            rwl.readLock().unlock();
        }

        rwl.writeLock().lock(); // 在外面获取锁
        try {
            long start = System.currentTimeMillis();
            System.out.println("线程" + thread.getName() + "开始写操作...");
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println("线程" + thread.getName() + "正在进行写操作...");
            }
            System.out.println("线程" + thread.getName() + "写操作完毕...");
        } finally {
            rwl.writeLock().unlock();
        }
    }


}

