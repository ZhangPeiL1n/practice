package com.zpl.practice.spring.aop.factory;

import org.springframework.stereotype.Component;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */
@Component
public class AOP {

    public void begin() {
        System.out.println("开始事务");
    }

    public void close() {
        System.out.println("关闭事务");
    }
}
