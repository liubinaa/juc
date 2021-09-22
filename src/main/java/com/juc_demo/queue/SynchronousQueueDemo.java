package com.juc_demo.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : liubin
 * @date : 2021/6/11 14:36
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                synchronousQueue.put("a");
                //如果没有执行take这里会阻塞
                 System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                //如果take拿不到数据，会阻塞，等待put生产，如果生产了重新获取
                String take = synchronousQueue.take();
                System.out.println(Thread.currentThread().getName() + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
