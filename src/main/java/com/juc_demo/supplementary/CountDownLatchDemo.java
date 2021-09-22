package com.juc_demo.supplementary;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author : liubin
 * @date : 2021/6/9 17:43
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        //表示有6个数
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行成功");
                //数量-1,线程还会继续向下执行
                countDownLatch.countDown();
            }).start();
        }
        try {
            //当数量为0时，才会往下继续执行，否则阻塞,可以设置阻塞时间
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成");
    }
}
