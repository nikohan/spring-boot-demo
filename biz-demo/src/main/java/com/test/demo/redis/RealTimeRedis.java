package com.test.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * redis local cache，可以与redis值保持一致，除非调用stop()后，不再同步
 * 注意：目前不是线程安全的
 * Created on 2017/12/12.
 */
public class RealTimeRedis {

	/**
	 * 完整redis事件响应
	 */
	private static final String RedisKeySpacePatternPrefix1 = "__keyspace@";
	private static final String RedisKeySpacePatternPrefix2 = "__:";

	private JedisPubSub jedisPubSub;

	private JedisPool jedisPool;
	private int dbIndex = 0;

	//本地redis缓存数据
	private Map<String, String> cache;

	//处理redis事件handlers
	private List<RedisEventHandler> handlers;

	private RealTimeRedis() {
		this.handlers = new ArrayList<>();
		this.cache = new ConcurrentHashMap<>();

		registerHandler(new FlushRedisEventHandler());
	}

	public static RealTimeRedis newInstance(String host, int port, long timeout, int dbIndex) {
		RealTimeRedis realTimeRedis = new RealTimeRedis();

		//init redis
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10000);
		config.setMaxIdle(20);
		config.setMaxWaitMillis(timeout);
		config.setTestOnBorrow(true);
		realTimeRedis.setJedisPool(new JedisPool(config, host, port));
		realTimeRedis.setDbIndex(dbIndex);

		JedisPubSub jedisPubSub = new JedisPubSub() {
			@Override
			public void onPSubscribe(String pattern, int subscribedChannels) {
				System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
			}

			@Override
			public void onPMessage(String pattern, String channel, String message) {
				if (!realTimeRedis.getCache().containsKey(channel)) {
					return;
				}

				final RedisEventContext redisEventContext = new RedisEventContext(
						pattern, channel, message, realTimeRedis.getCache(), realTimeRedis.getJedis());

				for (RedisEventHandler handler : realTimeRedis.getHandlers()) {
					handler.handle(redisEventContext);
				}
			}
		};
		realTimeRedis.setJedisPubSub(jedisPubSub);

		return realTimeRedis;
	}

	public String get(String redisKey) {
		return cache.get(getCacheKey(redisKey));
	}

	public String put(String redisKey, String value) {
		String cacheKey = getCacheKey(redisKey);
		String res = cache.put(cacheKey, value);
		syncSubscribe(cache.keySet());
		getJedis().set(redisKey, value);
		return res;
	}

	public String remove(String redisKey) {
		String cacheKey = getCacheKey(redisKey);
		String res = cache.remove(cacheKey);

		if (cache.isEmpty()) {
			stop();
		} else {
			syncSubscribe(cache.keySet());
		}
		getJedis().del(redisKey);
		return res;
	}

	public String getCacheKey(String redisKey) {
		return RedisKeySpacePatternPrefix1 + dbIndex + RedisKeySpacePatternPrefix2 + redisKey;
	}

	//同步订阅信息
	private void syncSubscribe(Set<String> existKeySet) {
		//转化为数组
		String[] existKeyArr = new String[existKeySet.size()];
		int i = 0;
		for (String key : existKeySet) {
			existKeyArr[i++] = key;
		}

		stop();
		Runnable runnable = () -> {
			//todo 单独放置，不会每次put时新启一个线程
			getJedis().psubscribe(jedisPubSub, existKeyArr);
		};

		Thread listenRedisThread = new Thread(runnable);
		listenRedisThread.setDaemon(true);
		listenRedisThread.start();
	}

	public void stop() {
		if (jedisPubSub.isSubscribed()) {
			//解除订阅信息
			jedisPubSub.punsubscribe();
		}
	}

	public boolean registerHandler(RedisEventHandler handler) {
		return handlers.add(handler);
	}

	private Jedis getJedis() {
		Jedis j = jedisPool.getResource();
		j.select(this.dbIndex);
		return j;
	}


	private JedisPubSub getJedisPubSub() {
		return jedisPubSub;
	}

	private void setJedisPubSub(JedisPubSub jedisPubSub) {
		this.jedisPubSub = jedisPubSub;
	}

	private Map<String, String> getCache() {
		return cache;
	}

	private void setCache(Map<String, String> cache) {
		this.cache = cache;
	}

	private List<RedisEventHandler> getHandlers() {
		return handlers;
	}

	private void setHandlers(List<RedisEventHandler> handlers) {
		this.handlers = handlers;
	}

	private JedisPool getJedisPool() {
		return jedisPool;
	}

	private void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	private int getDbIndex() {
		return dbIndex;
	}

	private void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}
}
