package com.zpl.practice.spring.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * 测试 spring 自动注入
 * bean 都在 spring 进行管理，按理来说可以直接将同类型的 bean 以集合的形式注入
 *
 * @author zhangpeilin
 * @date 2021/7/20
 */
@Configuration
public class BeanConfig {

    @Bean
    TestInterface returnBean1() {
        return new TestInterface() {
        };
    }

    @Bean
    TestInterface returnBean2() {
        return new TestInterface() {
        };
    }

    @Bean
    TestInterface returnBean3() {
        return new TestInterface() {
        };
    }

    /**
     * 测试注入集合
     *
     * @param interfaceList list
     * @param interfaceMap  map
     * @return
     */
    @Bean
    TestInterface returnBean4(@Autowired List<TestInterface> interfaceList,
                              @Autowired Map<String, TestInterface> interfaceMap) {
        System.out.println("注入 MapInterface 的 List" + interfaceList);
        System.out.println("注入 MapInterface 的 map" + interfaceMap);
        return new TestInterface() {
        };
    }
}
