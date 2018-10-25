package com.zpl.practice.spring.aop.annotation;

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
    public String doNothing(String name) {

        System.out.println("---------这里是方法体---------");
        return name;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-annotation.xml");

        IUser iUser = (IUser) context.getBean("userDao");
        //查看代理类型
        //System.out.println(iUser.getClass());

        //iUser.save();
        iUser.doNothing("parameter");
    }
}
