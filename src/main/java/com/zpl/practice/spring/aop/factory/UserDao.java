package com.zpl.practice.spring.aop.factory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */
//@Component
public class UserDao implements IUser {

    @Override
    public void save() {
        System.out.println("DB：保存用户");
    }

    @Override
    public void doNothing(String name) {

    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-factory.xml");

        //静态工厂
        //IUser iUser = (IUser) context.getBean("proxy");

        //非静态工厂
        IUser iUser = (IUser) context.getBean("iUser");

        System.out.println(iUser.getClass());

        iUser.save();
        //iUser.doNothing("这是参数");
    }
}
