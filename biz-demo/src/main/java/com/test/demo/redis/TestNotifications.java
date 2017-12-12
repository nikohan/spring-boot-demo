package com.test.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created on 2017/12/12.
 */
public class TestNotifications {
	public static void main(String[] args) {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.30.104", 6379);
		Jedis jedis = pool.getResource();
		jedis.select(4);
		jedis.set("RTnotify", "umq");
		jedis.set("RTnotify1", "umq");
	}
}
