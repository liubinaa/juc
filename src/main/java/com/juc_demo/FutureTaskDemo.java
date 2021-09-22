package com.juc_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : liubin
 * @date : 2021/6/9 17:28
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        new Thread( () -> {
            futureTask.run();
        }).start();
//        new Thread( () -> {
//            futureTask.run();
//        }).start();
        try {
            String s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyThread implements Callable<String> {
    @Override
    public String call() {
        System.out.println(Thread.currentThread().getName() + "执行成功");
        return "成功";
    }
}
