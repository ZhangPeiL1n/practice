package com.zpl.practice.spring.aop.weave;

import org.springframework.stereotype.Component;

/**
 * @author ZhangPeilin
 * @date 2018/10/23
 */

@Component("waiter")
public class NaiveWaiter implements Waiter {
    @Override
    public void greetTo(String clientName) {
        System.out.println("NaiveWaiter:greet to" + clientName);
    }

    @Override
    public void saveTo(String clientName) {
        System.out.println("NaiveWaiter:save to" + clientName);
    }
}
