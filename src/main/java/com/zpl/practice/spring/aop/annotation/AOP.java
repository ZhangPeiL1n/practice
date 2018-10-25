package com.zpl.practice.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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

    @Pointcut("execution(* com.zpl.practice.spring.aop.annotation.*.*(..))")
    public void pointCut() {
    }

    /**
     * 前置通知：在执行目标方法之前
     * 通过抛出一个异常来阻断目标方法的访问
     */
    @Before(value = "pointCut()")
    public void begin(JoinPoint joinPoint) {
        System.out.println("Before开始");
        System.out.println("参数列表");
        for (Object object : joinPoint.getArgs()) {
            System.out.println(object.toString());
        }
        System.out.println("被代理对象:" + joinPoint.getTarget());
        System.out.println("代理对象:" + joinPoint.getThis());
        System.out.println("方法签名：" + joinPoint.getSignature());
        System.out.println("declaringTypeName:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("declaringType" + joinPoint.getSignature().getDeclaringType());
        System.out.println("name:" + joinPoint.getSignature().getName());
        System.out.println("Before结束");
    }

    /**
     * 后置通知中无法获取方法的返回结果
     * <p>
     * 后置通知：在执行目标方法之后执行，无论是否出现异常最终都会执行
     */
    @After("pointCut()")
    public void close(JoinPoint joinPoint) {
        System.out.println("After开始");
        System.out.println("参数列表");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        System.out.println("被代理对象:" + joinPoint.getTarget());
        System.out.println("代理对象:" + joinPoint.getThis());
        System.out.println("方法签名：" + joinPoint.getSignature());
        System.out.println("declaringTypeName:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("declaringType" + joinPoint.getSignature().getDeclaringType());
        System.out.println("name:" + joinPoint.getSignature().getName());
        System.out.println("After结束");
    }


    /**
     * 与After的区别在于AfterReturning只会在方法执行成功之后才会被织入，
     * 如果After和AfterReturning位于同一文件中，写在前面的先执行
     *
     * @param joinPoint joinPoint
     * @param name      方法返回结果
     */
    @AfterReturning(value = "pointCut()", returning = "name")
    public void afterReturning(JoinPoint joinPoint, Object name) {
        System.out.println("方法返回结果：" + name);
    }


    //@AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing()");
    }


    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
        joinPoint.proceed();
        System.out.println("环绕后");
    }


}
