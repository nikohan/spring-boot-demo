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
@SpringBootApplication  //此注解可以自动扫描@Component的类，并将这些类放到spring容器中管理
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
