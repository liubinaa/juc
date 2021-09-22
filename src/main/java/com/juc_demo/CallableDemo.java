package com.juc_demo;

import java.util.concurrent.*;

/**
 * @author liubin
 */
public class CallableDemo implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //创建任务
        CallableDemo callableDemo = new CallableDemo();
        //提交执行
        Future<String> future = executorService.submit(callableDemo);
        //获取结果，这个里面阻塞
        String rs = future.get();
        System.out.println(rs);
        //关闭线程池
        executorService.shutdownNow();
    }

    @Override
    public String call() {
        return "String" + 1;
    }
}
