package com.test.demo.job.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;


/**
 * Created by zhaohan on 2018/7/3.
 */
public class CuratorPathChildrenCacheTest {

	public static void main(String[] args) throws Exception {

		CuratorFramework client = getClient();
		String parentPath = "/p1";

		PathChildrenCache pathChildrenCache = new PathChildrenCache(client,parentPath,true);
		pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
		pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				System.out.println("事件类型："  + event.getType() + "；操作节点：" + event.getData().getPath() + ", 数据：" + new String(event.getData().getData()));
			}
		});

		String path = "/p1/c1";
		client.create().withMode(CreateMode.PERSISTENT).forPath(path);
		Thread.sleep(1000); // 此处需留意，如果没有现成睡眠则无法触发监听事件

		client.setData().forPath(path,"1".getBytes());
		client.setData().forPath(path,"2".getBytes());
		client.setData().forPath(path,"3".getBytes());
		client.setData().forPath(path,"4".getBytes());
		client.setData().forPath(path,"5".getBytes());
		client.setData().forPath(path,"6".getBytes());

		client.delete().forPath(path);

		Thread.sleep(15000);

	}

	private static CuratorFramework getClient(){
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("127.0.0.1:2181")
				.retryPolicy(retryPolicy)
				.sessionTimeoutMs(6000)
				.connectionTimeoutMs(3000)
				.namespace("demo")
				.build();
		client.start();
		return client;
	}
}
