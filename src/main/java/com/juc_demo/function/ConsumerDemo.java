package com.juc_demo.function;

import java.util.function.Consumer;

/**
 * 消费型接口Consumer的Demo
 * @author : liubin
 * @date : 2021/6/16 10:42
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        consumer.accept("aaa");
    }
}
