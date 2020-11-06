package com.zpl.practice.java8.lambda;

/**
 * @author 张沛霖
 * @date 2020/10/19
 */
public class LambdaTest {
    public static void main(String[] args) {
        LambdaTest lambdaDemo = new LambdaTest();

        //类型声明
        MathOperation addition = (int a, int b) -> a + b;
        //不带类型声明
        MathOperation subtraction = (a, b) -> a - b;
        //大括号返回
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        //没有大括号及返回
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + lambdaDemo.operate(10, 5, addition));
    }


    interface MathOperation {
        int operation(int a, int b);
    }

    private int operate(int a, int b, MathOperation operation) {
        return operation.operation(a, b);
    }
}
