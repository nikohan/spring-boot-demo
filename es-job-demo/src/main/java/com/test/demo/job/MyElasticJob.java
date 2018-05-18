package com.test.demo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by zhaohan on 2018/5/18.
 */
public class MyElasticJob implements SimpleJob {

	@Override
	public void execute(ShardingContext context) {
		System.out.println("handle shard " + context.getShardingItem());
	}
}
