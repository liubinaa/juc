package com.juc_demo.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务
 * @author : liubin
 * @date : 2021/6/16 16:24
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private long start;
    private long end;

    private long temp = 10000;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 计算方法
     * @return 返回值
     */
    @Override
    protected Long compute() {
        if (end - start < temp) {
            long sum = 0;
            for (long i = start; i < end; i ++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = start + (end - start) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
