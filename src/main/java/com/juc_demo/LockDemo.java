package com.jucdemo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : liubin
 * @date : 2021/5/21 14:19
 */
@Getter
@Setter
@ToString
public class LockDemo {
    /**
     * 可以选择是公平重入锁和非公平重入锁，入参是true是公平锁，false是非公平锁，默认是非公平锁
     */
    static Lock lock = new ReentrantLock();
    private static int count = 0;
    public static void incr() {
        try {
            Thread.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
          获得锁
          所有获得锁下面的操作要有finally,来释放锁
         */
        lock.lock();
        try {
            count ++;
        } finally {
          //释放锁
          lock.unlock();
        }
    }
}
