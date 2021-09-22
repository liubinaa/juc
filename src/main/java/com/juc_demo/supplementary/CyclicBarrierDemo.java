package com.juc_demo.supplementary;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author : liubin
 * @date : 2021/6/9 17:53
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //后面的是动作，满足7个后的操作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七科龙珠，召唤神龙");
        });
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行成功");
                try {
                    //线程阻塞，数量 + 1,满足cyclicBarrier的数量后才会往下执行
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("aaaa");
            }).start();
        }
    }
}
