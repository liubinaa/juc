package com.juc_demo.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : liubin
 * @date : 2021/6/10 17:06
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    /**
     * 会报错的方法
     * ：add，remove，element
     */
    public static void test1() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        blockingQueue.add("d");
        blockingQueue.remove();
        //检测队首元素
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue);
    }

    /**
     * 不会报错的方法：offer，poll，peek
     */
    public static void test2() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("d");
        blockingQueue.poll();
        //检测队首元素
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue);
    }

    /**
     * 阻塞的方法：put，take
     */
    public static void test3 () throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        System.out.println(blockingQueue);
    }

    /**
     * 超时等待的方法：offer，poll指定超时等待时间，超过则不执行这个插入，移除
     */
    public static void test4 () throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a", 1, TimeUnit.SECONDS);
        blockingQueue.offer("b", 1, TimeUnit.SECONDS);
        blockingQueue.offer("c", 1, TimeUnit.SECONDS);
        blockingQueue.offer("d", 1, TimeUnit.SECONDS);
        blockingQueue.poll(1, TimeUnit.SECONDS);
        blockingQueue.poll(1, TimeUnit.SECONDS);
        blockingQueue.poll(1, TimeUnit.SECONDS);
        System.out.println(blockingQueue);
    }
}
