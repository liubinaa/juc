package com.juc_demo.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步去执行Runnable的代码，通过get阻塞使主线程等待
 * @author : liubin
 * @date : 2021/6/17 15:04
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       Future<Void> completableFuture = CompletableFuture.runAsync(() -> {
           try {
               TimeUnit.SECONDS.sleep(2);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(Thread.currentThread().getName());
        });
        System.out.println("aaa");
        //主线程阻塞，获取执行结果
        completableFuture.get();
        System.out.println("bbb");

        CompletableFuture<String> supplyCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            int i = 1/0;
            return "200";
        });
        System.out.println(supplyCompletableFuture.whenComplete((t,u) -> {
            System.out.println(t);
            System.out.println(u);
        }).exceptionally(e -> {
            e.printStackTrace();
            return "500";
        }).get());
    }
}
