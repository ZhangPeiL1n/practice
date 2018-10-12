package com.zpl.practice.initialization;

/**
 * 成员变量初始化的位置
 * 无继承类成员变量的初始化
 *
 * @author ZhangPeilin
 * @date 2018/10/10
 */

public class Init {
    /**
     * 声明变量name时，将name初始化为张三
     */
    private String name = "张三";

    public Init(){
        //输出name,此时为张三
        System.out.println(name);
        //将name赋值为李四
        this.name = "李四";
    }

    public static void main(String[] args) {
        //输出张三
        Init init = new Init();
        //输出李四
        System.out.println(init.name);
    }

}
