package com.juc_demo.function;

import java.util.function.Function;

/**
 * 函数式接口function的Demo
 * @author : liubin
 * @date : 2021/6/15 18:02
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<String, Boolean> function  = "a"::equals;
        System.out.println(function.apply("a"));
    }
}
