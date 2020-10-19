package com.zpl.practice.spring.autowired;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ZhangPeilin
 * @date 2018/11/26
 */
@Component
public class TestBean {

    private final Map<String, MapInterface> interfaceMap;

    public TestBean(Map<String, MapInterface> interfaceMap) {
        this.interfaceMap = interfaceMap;
        System.out.println("Bean的map:" + interfaceMap);
        System.out.println("Bean的map:" + this.interfaceMap);
    }
}
