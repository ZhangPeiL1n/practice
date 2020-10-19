package com.zpl.practice.mybatis;

import lombok.Getter;
import lombok.Setter;

/**
 * student表实体
 *
 * @author ZhangPeilin
 * @date 2018/11/21
 */

@Getter
@Setter
public class Student {
    private Integer id;
    private String name;
    private Double sal;

    public Student() {
    }

    public Student(Integer id, String name, Double sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }
}
