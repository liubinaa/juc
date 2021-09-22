package com.juc_demo.demo;

/**
 * @author : liubin
 * @date : 2021/8/5 9:29
 */
public class Demo1 {
    public static void main(String[] args) {
        String s1= "ab";
        String s = new String("a") + new String("b");
        String intern = s.intern();
        System.out.println(s == intern);
        System.out.println(s1 == intern);
    }
}
