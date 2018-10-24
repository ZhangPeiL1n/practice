package com.zpl.practice.spring.aop.weave;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */


@Aspect
@Component
public class EnableSellerAspect {

    @DeclareParents(value = "com.zpl.practice.spring.aop.weave.NaiveWaiter", defaultImpl = SmartSeller.class)
    public Seller seller;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Waiter waiter = (Waiter) context.getBean("waiter");

        waiter.greetTo("zpl");
        waiter.saveTo("zpl");

        Seller seller = (Seller) waiter;
        seller.sell("nintendo switch", "zpl");
    }
}
