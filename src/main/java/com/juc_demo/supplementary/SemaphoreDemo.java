package com.juc_demo.supplementary;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : liubin
 * @date : 2021/6/9 18:14
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //信号量数量，可以理解成停车位，常用于限流
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //获取，如果已经满了，等待，等待被释放为止
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放，会将当前的信号量释放，唤醒等待的线程
                    semaphore.release();
                }
            }).start();
        }
    }
}
