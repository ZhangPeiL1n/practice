package com.zpl.practice.spring.aop.xml;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */

public class AOP {

    public void begin() {
        System.out.println("开始事务");
    }


    public void close() {
        System.out.println("关闭事务");
    }


}
