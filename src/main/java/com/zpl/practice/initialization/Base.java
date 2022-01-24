package com.zpl.practice.initialization;

/**
 * 父类
 *
 * @author ZhangPeilin
 * @date 2018/10/10
 */

public class Base {

    public String baseName = "Base";

    public Base() {
        callName();
    }

    public void callName() {
        System.out.println("base");
    }
}
