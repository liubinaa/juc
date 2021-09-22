package com.juc_demo.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : liubin
 * @date : 2021/6/8 17:22
 */
public class CopyOnWriteListDemo {
    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>(16);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0,8), new Object());
                System.out.println(map);
            }).start();
        }
    }
}
