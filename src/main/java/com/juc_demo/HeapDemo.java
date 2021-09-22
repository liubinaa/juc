package com.juc_demo;

import java.util.concurrent.TimeUnit;

/**
 * @author : liubin
 * @date : 2021/7/21 14:42
 */
public class HeapDemo {
    public static void main(String[] args) {
        System.out.println("start");
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
