package com.zpl.practice.spring.aop.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhangPeilin
 * @date 2018/10/24
 */
//@Component
public class OrderDao {
    public void save() {
        System.out.println("我已经进货了");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-annotation.xml");
        OrderDao orderDao = (OrderDao) context.getBean("orderDao");

        System.out.println(orderDao.getClass());
        orderDao.save();
    }
}
