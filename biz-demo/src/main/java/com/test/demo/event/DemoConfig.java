package com.test.demo.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhaohan on 2016/8/3.
 */
@Configuration
public class DemoConfig {
    @Bean
    public DemoService demoService() {
        System.out.println("DemoService...");
        DemoService demoService = new DemoService();
        return demoService;
    }
}
