package com.test.demo.redis;

/**
 * Created on 2017/12/12.
 */
public interface RedisEventHandler {

	/**
	 * 处理redis事件
	 * @param context redis事件
	 */
	void handle(RedisEventContext context);
}
