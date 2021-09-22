package com.juc_demo.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用练习，带版本号的原子操作
 * 练习时注意：Long类型和Integer类型的缓存问题
 * @author : liubin
 * @date : 2021/6/21 13:55
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Long> atomicStampedReference = new AtomicStampedReference<>(1L, 1);
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(1L, 3L, stamp, stamp + 1));
        },"A").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(1L, 3L, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + atomicStampedReference.compareAndSet(3L, 1L, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
        },"B").start();
    }
}
