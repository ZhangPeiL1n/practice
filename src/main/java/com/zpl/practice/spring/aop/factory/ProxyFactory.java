package com.zpl.practice.spring.aop.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */


public class ProxyFactory {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        AOP aop = new AOP();
        InvocationHandler invocationHandler = (proxy, method, args1) -> {
            //前置增强
            aop.begin();
            //System.out.println(args);
            System.out.println("调用了" + proxy + "的" + method.getName() + "方法");
            if (args1 != null && args1.length > 0) {
                for (Object object : args1) {
                    System.out.println(object.toString());
                }
            }
            Object returnValue = method.invoke(proxy, args1);
            //后置增强
            aop.close();
            return returnValue;
        };
        Object proxyInstance = Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), invocationHandler);

        ((IUser) proxyInstance).save();
    }
}
