package com.juc_demo.function;

import java.util.function.Predicate;

/**
 * 断定式接口Predicate的Demo
 * @author : liubin
 * @date : 2021/6/16 10:26
 */
public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate = "a"::equals;
        System.out.println(predicate.test("a"));
    }
}
