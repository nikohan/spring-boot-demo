package com.test.demo.job;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by zhaohan on 2018/5/18.
 */
public class JobDemo {
	public static void main(String[] args) {
//		new JobScheduler(createRegistryCenter(), createJobConfiguration(), createJobEventConfig()).init();
		new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
	}

	private static CoordinatorRegistryCenter createRegistryCenter() {
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
				new ZookeeperConfiguration("localhost:2181", "es-job-demo"));
		regCenter.init();
		//添加监听器
		CuratorFramework client = (CuratorFramework) regCenter.getRawClient();
		client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {
				System.out.println("connection state : " + newState.name());
			}
		});
		return regCenter;
	}

	private static LiteJobConfiguration createJobConfiguration() {
		// 定义作业核心配置
		JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 10).build();
		// 定义SIMPLE类型配置
		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, MyElasticJob.class.getCanonicalName());
		// 定义Lite作业根配置
		LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
		return simpleJobRootConfig;
	}

	private static JobEventConfiguration createJobEventConfig() {
		DriverManagerDataSource ds = new DriverManagerDataSource ();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/elastic-job?useSSL=false");
		ds.setUsername("root");
		ds.setPassword("123456");

		// 定义日志数据库事件溯源配置
		JobEventConfiguration jobEventRdbConfig = new JobEventRdbConfiguration(ds);
		return jobEventRdbConfig;
	}
}
