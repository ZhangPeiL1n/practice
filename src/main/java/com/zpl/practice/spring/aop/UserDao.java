package com.zpl.practice.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */
@Component
public class UserDao implements IUser {

    @Override
    public void save() {
        System.out.println("DB：保存用户");
    }

    @Override
    public void doNothing(String name) {

    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        IUser iUser = (IUser) context.getBean("userDao");

        iUser.save();
        //iUser.doNothing("这是参数");
    }
}
