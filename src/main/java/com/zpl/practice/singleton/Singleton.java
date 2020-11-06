package com.zpl.practice.singleton;

/**
 * dcl 双检查锁 创建单例
 * <p>
 * 1. final 防止类被继承
 * 2. 私有构造器防止外部创建实例
 * 3. 在第一次创建实例时，使用 synchronized 关键字来防止并发 导致创建两个实例
 * 4. 当两个线程都通过第一个 instance == null的判断后，第一个线程创建完实例释放 monitor 后，
 * 第二个线程获取 monitor 后再进行一次检查，防止重复创建实例
 *
 * @author 张沛霖
 */
public final class Singleton {
    private Singleton() {

    }

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
