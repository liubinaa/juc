package com.juc_demo.pool;

import java.util.concurrent.*;

/**
 * 线程池的整体流程：先执行核心数量的线程，多余的放到阻塞队列中，阻塞队列满了，
 * 执行最大核心数量执行后续线程，然后再执行阻塞队列中的线程。
 * 再执行阻塞队列中的线程前，最大核心数量也满了，会触发拒绝策略
 *
 * AbortPolicy：如果满了，还有人进来，不处理这个人的
 * CallerRunsPolicy：哪来的回哪去
 * DiscardPolicy：队列满了，丢掉当前任务，不会抛出异常
 * DiscardOldestPolicy：队列满了，抛弃队列里最老的那个
 * 这里会被抛弃的是数字3和4，当核心3 + 阻塞队列3满了的时候，
 * 7.8进入使用最大核心额外的两个，9，10进入的时候会触发拒绝策略，挤掉阻塞队列中最老的元素3，4
 * @author : liubin
 * @date : 2021/6/15 9:57
 */
public class ThreadPoolExecutorsDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    threadPoolExecutor.execute(() -> {
                        System.out.println(Thread.currentThread().getName() + "执行" + finalI);
                    });
                });
            }
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPoolExecutor.shutdown();

        }
    }
}
