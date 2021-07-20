package com.zpl.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author zhangpeilin
 * @date 2021/8/19
 */
@EnableScheduling
@Component
public class Tasks {
    @Autowired
    Test test;

    @Autowired(required = false)
    Tasks(){

    }
    @Autowired(required = false)
    Tasks(String a){

    }
    //
    // @Scheduled(cron = "* * * * * *")
    // public void testLambda(){
    //     test.testThread();
    // }
}
