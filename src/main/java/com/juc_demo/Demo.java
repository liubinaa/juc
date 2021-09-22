package com.juc_demo;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author : liubin
 * @date : 2021/7/14 16:13
 */
public class Demo {
    private static int num = 1;
    static {
        num = 2;
        number = 20;
    }
    static int number = 10;
    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(number);
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }
    }
}
