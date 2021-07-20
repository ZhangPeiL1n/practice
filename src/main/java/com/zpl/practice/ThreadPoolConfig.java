package com.zpl.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpeilin
 * @date 2021/8/19
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("testThreadPool")
    public ThreadPoolExecutor initThreadPool(){
        return new ThreadPoolExecutor(1,10,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),new ThreadPoolExecutor.AbortPolicy());
    }

}
