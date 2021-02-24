package com.zpl.practice.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 张沛霖
 * @date 2021/2/24
 */
public class OOMDemo1 {
    public static void main(String[] args) {
        long counter = 0;

        while (true) {
            final Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("run")) {
                        System.out.println("进行安全检查");
                        return methodProxy.invokeSuper(o, objects);
                    } else {
                        return methodProxy.invokeSuper(o, objects);
                    }
                }
            });

            Car car = (Car) enhancer.create();
            car.run();

            System.out.printf("共创建了 %d 个 Car 的子类%n", counter++);
        }
    }


    static class Car {
        public void run() {
            System.out.println("汽车启动，开始行使......");
        }
    }

    static class SafeCar extends Car {

        @Override
        public void run() {
            System.out.println("汽车启动，开始行使......");
            super.run();
        }
    }
}
