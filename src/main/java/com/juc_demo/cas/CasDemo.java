package com.juc_demo.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS：compareAndSet 比较并交换，底层使用unsafe类调用 native的c++来操作内存
 * @author : liubin
 * @date : 2021/6/21 13:17
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2021);
        //如果是期望的值，则更新为更新的值，否则不执行更新，CAS是CPU的原语
        System.out.println(atomicInteger.compareAndSet(2021, 2023));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement());

    }
}
