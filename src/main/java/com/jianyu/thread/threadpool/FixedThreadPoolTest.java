package com.jianyu.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定大小的线程池
 * @author BaiJianyu <br>
 * @date 2018年5月14日下午3:21:37 <br>
 * Better late than never. <br>
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;

            fixedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
