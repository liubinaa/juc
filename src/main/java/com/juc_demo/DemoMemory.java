package com.juc_demo;

import lombok.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author : liubin
 * @date : 2021/7/12 11:23
 */
public class DemoMemory {
    public static void main(String[] args) {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        //这里的结果为什么要比设置值少？因为s0和s1必须有一个是空的，抛掉了一个幸存者区
        System.out.println(max/1024/1024);
        System.out.println(total/1024/1024);

        int i = 10;
        int j = i ++ + ++ i;
        System.out.println(j);
        System.out.println(i);

        @NonNull Date calendarDate = new Date();
        calendarDate.setTime(calendarDate.getTime() + 60 * 60 * 7 * 1000L);
        Date endDate = new Date(calendarDate.getTime() + 60 * 60 * 24 * 1000L);
        System.out.println(calendarDate);
        System.out.println(endDate);
    }
}
