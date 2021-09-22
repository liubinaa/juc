package com.juc_demo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author : liubin
 * @date : 2021/6/17 13:17
 */
public class TestDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        fun1();
        fun2();
        fun3();
    }

    static void fun1() {
        long sum = 0;
        long start = System.currentTimeMillis();
        for (long i = 0; i < 10_0000_0000; i ++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "时间" + (end - start));
    }

    static void fun2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinDemo(0, 10_0000_0000);
        //execute方法不返回任何值，submit方法返回对象本身，invoke方法返回join结果
        pool.execute(forkJoinTask);
        //fork方法是异步执行，join和get方法是同步执行，但join方法不能中断，get方法会抛异常
        Long sum = forkJoinTask.get();
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "时间" + (end - start));
    }

    static void fun3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10_0000_0000 - 1).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + "时间" + (end - start));
    }
}
