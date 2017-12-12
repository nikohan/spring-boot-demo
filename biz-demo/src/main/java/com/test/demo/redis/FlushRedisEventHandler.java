package com.test.demo.redis;

/**
 * Created on 2017/12/12.
 */
public class FlushRedisEventHandler implements RedisEventHandler {

	@Override
	public void handle(RedisEventContext context) {
		//更新本地缓存
		context.flushCache();
	}
}
