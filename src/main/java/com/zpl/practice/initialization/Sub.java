package com.zpl.practice.initialization;

/**
 * 子类
 *
 * @author ZhangPeilin
 * @date 2018/10/10
 */

public class Sub extends Base {

    public String baseName = "Sub";

    public Sub() {
        System.out.println(baseName);
    }

    @Override
    public void callName() {
        System.out.println(baseName);
    }

    public static void main(String[] args) {
        //Base b = new Sub();
        Sub sub = new Sub();
    }

}
