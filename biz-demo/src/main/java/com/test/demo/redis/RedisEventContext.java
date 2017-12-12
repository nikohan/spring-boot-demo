package com.test.demo.redis;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created on 2017/12/12.
 */
public class RedisEventContext {
	private String pattern;

	private String channel;

	private String command;

	private Map<String, String> cache;

	private Jedis jedis;

	private String redisKey;

	private String redisValue;

	public RedisEventContext(String pattern, String channel, String command, Map<String, String> cache, Jedis jedis) {
		this.pattern = pattern;
		this.channel = channel;
		this.command = command;
		this.cache = cache;
		this.jedis = jedis;
		this.redisKey = toRedisKey(channel);
	}

	private static String toRedisKey(String channel) {
		int index = channel.indexOf(":");
		if (index < 0) {
			return "";
		} else {
			index ++;
		}
		return channel.substring(index, channel.length());
	}

	//更新本地缓存
	public String flushCache() {
		return cache.put(channel, getRedisValue());
	}

	public String getRedisValue() {
		if (StringUtils.isEmpty(redisValue)) {
			redisValue = jedis.get(redisKey);
		}
		return redisValue;
	}

	public String getPattern() {
		return pattern;
	}

	public String getChannel() {
		return channel;
	}

	public String getCommand() {
		return command;
	}

	public String getRedisKey() {
		return redisKey;
	}
}
