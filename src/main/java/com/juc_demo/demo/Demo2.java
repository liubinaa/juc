package com.juc_demo.demo;

import java.math.BigDecimal;

/**
 * @author : liubin
 * @date : 2021/8/18 15:47
 */
public class Demo2 {
    public static void main(String[] args) {
        String upperLimit = "120%";
        String substring = upperLimit.substring(0, upperLimit.length() - 1);
        System.out.println(substring);
        BigDecimal bigDecimal = new BigDecimal(substring);
        System.out.println(bigDecimal.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
    }
}
