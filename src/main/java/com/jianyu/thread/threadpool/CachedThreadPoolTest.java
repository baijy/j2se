package com.jianyu.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 可缓存的线程池测试
 * @author BaiJianyu <br>
 * @date 2018年5月14日下午3:06:40 <br>
 * Better late than never. <br>
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }
}
