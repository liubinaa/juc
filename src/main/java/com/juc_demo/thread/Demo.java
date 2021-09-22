package com.juc_demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author : liubin
 * @date : 2021/6/4 14:39
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
