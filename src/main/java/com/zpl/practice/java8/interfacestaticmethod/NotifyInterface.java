package com.zpl.practice.java8.interfacestaticmethod;

/**
 * @author 张沛霖
 * @date 2020/11/5
 */
public interface NotifyInterface {

    void doNotify(String msg);
    // //测一下匿名内部类和lambda表达式的区别，加深印象
    // void doNotify(String msg,String text);

    static NotifyInterface byEmail() {
        return msg -> {
            System.out.println(msg + " by email");
        };

        // //匿名内部类实现
        // return new NotifyInterface() {
        //     @Override
        //     public void doNotify(String msg) {
        //
        //         System.out.println(msg);
        //     }
        //
        //     @Override
        //     public void doNotify(String msg, String text) {
        //
        //         System.out.println(msg + text);
        //     }
        // };
    }

    static NotifyInterface bySms() {
        return msg -> {
            System.out.println(msg + " by sms");
        };
    }

    static NotifyInterface byWechat() {
        return msg -> {
            System.out.println(msg + " by wechat");
        };
    }
}
