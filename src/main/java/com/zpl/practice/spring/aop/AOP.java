package com.zpl.practice.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */
@Component
@Aspect
public class AOP {

    /**
     * execution的写法
     * https://blog.csdn.net/u012887385/article/details/54600706
     * <p>
     * 一个错误：warning no match for this type name
     * https://blog.csdn.net/yangxiaovip/article/details/31797475
     */
    @Before("execution(* com.zpl.practice.spring.aop.*.*(..))")
    public void begin() {
        System.out.println("开始事务");
    }

    @After("execution(* com.zpl.practice.spring.aop.*.*(..))")
    public void close() {
        System.out.println("关闭事务");
    }
}
