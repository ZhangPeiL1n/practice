package com.zpl.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableRedisHttpSession
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

    //@Bean
    // public CommandLineRunner commandLineRunner(ApplicationContext context){
    // 	return args -> {
    // 		System.out.println("the beans provided by spring boot");
    // 		String[] names = context.getBeanDefinitionNames();
    // 		for (String name : names){
    // 			System.out.println(name);
    // 		}
    // 	};
    // }
}
