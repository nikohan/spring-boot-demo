package com.test.demo.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * entry of application
 * Created by nikohan on 2016/7/24.
 */
@EnableWebFlux
@SpringBootApplication
public class ApplicationV2 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationV2.class);
    }
}
