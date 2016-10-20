package com.test.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created on 2016/10/20.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    private ApiInfo testApiInfo() {
        ApiInfo apiInfo = new ApiInfo("标题文档",//大标题
                "文档的详细说",//小标题
                "0.1",//版本
                "NO terms of service",
                "razorer@razorer.com",//作者
                "The Apache License, Version 2.0",//链接显示文字
                "www.razorer.com"//网站链接
        );

        return apiInfo;
    }
}
