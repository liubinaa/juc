package com.juc_demo.thread.method;

/**
 * @author : liubin
 * @date : 2021/6/2 16:45
 */
public class TestThreadStop implements Runnable{
    private volatile boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "执行了" + i++ + "次");
            if (i == 1000) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            }
        }
    }

    public static void main(String[] args) {
        TestThreadStop testThreadStop = new TestThreadStop();
        Thread thread = new Thread(testThreadStop);
        thread.start();

        TestThreadStop testThreadStop2 = new TestThreadStop();
        Thread thread2 = new Thread(testThreadStop);
        thread2.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
