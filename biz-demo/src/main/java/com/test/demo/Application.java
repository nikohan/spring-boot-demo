package com.test.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * entry of application
 * Created by nikohan on 2016/7/24.
 */
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
