package com.zpl.practice;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 张沛霖
 * @date 2020/9/14
 */
// @ControllerAdvice
public class TestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public void ExceptionHandler(Exception e) {
        System.out.println("进入统一异常捕获");
        e.printStackTrace();
    }
}
