package com.test.demo.job.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 测试zk watch 是否会丢失
 * Created by zhaohan on 2018/7/3.
 */
public class CuratorTreeCacheTest {

	public static void main(String[] args) throws Exception {

		CuratorFramework client = getClient();
		String path = "/p1";
		final TreeCache treeCache = new TreeCache(client,path);
		treeCache.start();
		treeCache.getListenable().addListener(new TreeCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				if (event == null || event.getData() == null) {
					return;
				}
				System.out.println("事件类型："  + event.getType() + "；操作节点：" + event.getData().getPath() + ", 数据：" + new String(event.getData().getData()));
			}
		});
		client.setData().forPath(path,"1".getBytes());
		client.setData().forPath(path,"2".getBytes());
		client.setData().forPath(path,"3".getBytes());
		client.setData().forPath(path,"4".getBytes());
		client.setData().forPath(path,"5".getBytes());
		client.setData().forPath(path,"6".getBytes());
		Thread.sleep(10000);
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
