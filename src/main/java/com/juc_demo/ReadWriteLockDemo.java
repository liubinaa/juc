package com.juc_demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读者写者问题更直接的处理方式
 * 读者 - 读者 共享
 * 读者 - 写者 不共享
 * 写者 - 写者 不共享
 * @author : liubin
 * @date : 2021/5/24 13:09
 */
@Getter
@Setter
@ToString
public class ReadWriteLockDemo {
    private static Map<String, Object> cacheMap = new HashMap<>();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    /**
     * 缓存的更新和读取的时候
     * @param key key
     * @return 结果
     */
    public static Object get(String key) {
        //读锁
        readLock.lock();
        try {
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static Object set(String key, Object value) {
        //写锁
        writeLock.lock();
        try {
            return cacheMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
