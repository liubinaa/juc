package com.juc_demo;

/**
 * 生产者消费者问题练习
 * @author : liubin
 * @date : 2021/6/7 13:53
 */
public class SyncProductConsumeDemo {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.product();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.consume();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.product();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                data.consume();
            }
        }, "D").start();
    }
}

class Data {
    int num = 0;
    public synchronized void product() {
        //这里一定要使用while，而不是if，为了防止虚假唤醒。
        //线程也可以唤醒，而不会被通知，中断或超时，即所谓的虚假唤醒，等待应该总是出现在循环中
        //如果使用if，只判断一次，使用while的时候，会再次判断状态
        while (num != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num ++;
        System.out.println(Thread.currentThread().getName() + "生产，剩余" + num);
        notifyAll();
    }

    public synchronized void consume() {
        while (num <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        num --;
        System.out.println(Thread.currentThread().getName() + "消费，剩余" + num);
        notifyAll();
    }
}