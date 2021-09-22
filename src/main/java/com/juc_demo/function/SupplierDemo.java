package com.juc_demo.function;

import java.util.function.Supplier;

/**
 * 供给型接口Supplier的Demo
 * @author : liubin
 * @date : 2021/6/16 10:46
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "str";
        System.out.println(supplier.get());
    }
}
