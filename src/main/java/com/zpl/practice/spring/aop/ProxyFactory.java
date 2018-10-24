package com.zpl.practice.spring.aop;

import java.lang.reflect.Proxy;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */


public class ProxyFactory {


    public Object getProxyInstance(Object target, AOP aop) {

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            aop.begin();
            //System.out.println(args);
            System.out.println("调用了" + target + "的" + method.getName() + "方法");
            if (args != null && args.length > 0) {
                for (Object object : args) {
                    System.out.println(object.toString());
                }
            }
            Object returnValue = method.invoke(target, args);
            aop.close();

            return returnValue;
        });
    }
}
