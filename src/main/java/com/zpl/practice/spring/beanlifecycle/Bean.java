package com.zpl.practice.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 普通bean
 *
 * @author ZhangPeilin
 * @date 2018/12/04
 */

public class Bean implements BeanFactoryAware, BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {


    private String property;

    public String getProperty() {
        return property;
    }

    /**
     * 成员变量的setter
     *
     * @param property
     */
    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * 执行顺序：1.构造方法
     *          (这里调用属性的setter注入属性值)
     *          2.setBeanName   (BeanNameAware接口)
     *          3.setBeanFactory    (BeanFactoryAware接口)
     *          4.setApplicationContext (ApplicationContextAware接口)
     *          5.postProcessBeforeInitialization   (BeanPostProcessor，不是bean的方法)
     *          6.postConstruct (@PostConstruct)
     *          7.afterPropertySet  (InitializingBean接口)
     *          8.init-method   (init-method属性)
     *          9.postProcessAfterInitialization    (BeanPostProcessor，不是bean的方法)
     *          (初始化方法到这里，以下是销毁方法)
     *          10.preDestroy   (@PreDestroy)
     *          11.destroy  (DisposableBean接口)
     *          12.destroy-method   （destroy-method属性）
     */

    /**
     * 构造方法
     */
    public Bean() {
        System.out.println("construct");
    }

    /**
     * BeanNameAware接口方法
     *
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName,property:" + getProperty());
    }

    /**
     * BeanFactoryAware接口方法
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    /**
     * ApplicationContextAware接口方法
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }

    /**
     * bean的init-method
     */
    public void initMethod() {
        System.out.println("initMethod,property:" + getProperty());
    }

    /**
     * bean的destroy-method
     */
    public void destroyMethod() {
        System.out.println("destroyMethod");
    }


    /**
     * '@PostConstruct' 这个注解和'@PreDestroy'有点像init-method和destroy-method这两个方法
     * 如果在注解方式生成bean时，可以考虑使用'@PostConstruct'注解来替代init-method来完成初始化的任务，
     * 同样也可以使用'@PreDestroy'注解来完成销毁的任务
     * 这两个注解执行的位置也像名字的含义：构造方法之后，销毁之前
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy");
    }


    /**
     * DisposableBean接口方法
     */
    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    /**
     * InitializingBean接口方法
     */
    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet,property:" + getProperty());
    }


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Bean bean = (Bean) context.getBean("bean");
        context.close();
    }
}
