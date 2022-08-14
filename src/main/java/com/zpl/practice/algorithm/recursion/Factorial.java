package com.zpl.practice.algorithm.recursion;

/**
 * 阶乘
 * 递归
 *
 * @author ZhangPeiL1n
 * @date 2022/8/14 14:16
 **/
public class Factorial {


    public static int factorial(int base) {
        // 基线条件
        if (base == 1) {
            return 1;
        // 递归条件
        } else {
            return base * factorial(base - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(6));
    }
}
