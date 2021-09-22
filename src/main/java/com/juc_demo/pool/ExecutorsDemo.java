package com.juc_demo.pool;

import java.util.concurrent.*;

/**
 * Executors 工具类 三大方法
 * @author : liubin
 * @date : 2021/6/15 9:57
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        //单个线程
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        //创建一个固定的线程池的大小
        ExecutorService fixedService = Executors.newFixedThreadPool(5);
        //可伸缩的，遇强则强，遇弱则弱
        ExecutorService cachedService = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                singleExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "执行");
                });
            }
        } finally {
            //线程池用完，程序结束，关闭线程池
            singleExecutor.shutdown();
        }
    }
}
