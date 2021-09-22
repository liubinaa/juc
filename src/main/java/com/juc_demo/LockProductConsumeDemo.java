package com.juc_demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者问题练习
 * @author : liubin
 * @date : 2021/6/7 15:40
 */
public class LockProductConsumeDemo {
    public static void main(String[] args) {
        Data2 data = new Data2();
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

class Data2 {
    int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void product() {
        lock.lock();
        try {
            while (num != 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num ++;
            System.out.println(Thread.currentThread().getName() + "生产，剩余" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (num <= 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num --;
            System.out.println(Thread.currentThread().getName() + "消费，剩余" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
