package com.zpl.practice.java8.interfacestaticmethod;

import lombok.Getter;

/**
 * 以往我以为的策略模式需要去编写很多的类去实现策略的接口，因为需要不同的实例来去做不同的处理。
 * 其实也是需要不同的实例的去做不同逻辑的处理，只不过这里的重点是不同的实例
 * 思维误区是以为不同的实例需要写不同的类，而静态工厂模式解决了创建不同实例的问题，而匿名内部类解决了要写不同处理逻辑的问题
 * <p>
 * 其实是 策略模式 + 静态工厂（静态方法返回实例） 两种模式结合起来了，只不过 java8 接口支持静态方法后直接将静态工厂移入到接口中，
 * 然后静态工厂配合 lambda 表达式或者 匿名内部类 的方式去创建实例
 *
 * @author 张沛霖
 * @date 2020/11/5
 */
@Getter
public enum NotifyType {
    /**
     * 邮件
     */
    email("邮件", NotifyInterface.byEmail()),
    /**
     * 短息
     */
    sms("短信", NotifyInterface.bySms()),
    /**
     * 微信
     */
    wechat("微信", NotifyInterface.byWechat());

    String desc;
    NotifyInterface notifyInterface;

    NotifyType(String desc, NotifyInterface notifyInterface) {
        this.desc = desc;
        this.notifyInterface = notifyInterface;
    }


    public static void main(String[] args) {
        String type = "email";
        String msg = "信息";
        NotifyType.valueOf(type).getNotifyInterface().doNotify(msg);
        // NotifyType email = NotifyType.email;
        // System.out.println(email.name());
    }
}
