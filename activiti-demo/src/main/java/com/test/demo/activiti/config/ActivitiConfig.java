package com.test.demo.activiti.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created on 2018/1/17.
 */
@Configuration
public class ActivitiConfig {

	@Autowired
	PlatformTransactionManager transactionManager;

	@Autowired
	DruidDataSource druidDataSource;

	@Bean
	public SpringProcessEngineConfiguration getProcessEngineConfiguration(){
		SpringProcessEngineConfiguration config =
				new SpringProcessEngineConfiguration();
		config.setDataSource(druidDataSource);
		config.setTransactionManager(transactionManager);
		config.setDatabaseType("mysql");
		config.setDatabaseSchemaUpdate("true");
		return config;
	}
}
