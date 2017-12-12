package com.test.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 需要redis修改配置
 * 127.0.0.1:6379> CONFIG SET notify-keyspace-events KEA
 * 或者修改配置文件redis.conf的notify-keyspace-events "KEA"
 *
 * http://xiaorui.cc/2016/12/22/redis-keyspace%E9%94%AE%E9%80%9A%E7%9F%A5%E7%9A%84%E9%82%A3%E4%BA%9B%E4%BA%8B%E5%84%BF/
 * http://intotheprogramming.blogspot.com/2016/03/redis-keyspace-notifications-java.html
 * http://www.imooc.com/article/10407
 * Created on 2017/12/12.
 */
public class Subscriber {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.30.104", 6379);
		JedisPubSub jedisPubSub = new JedisPubSub() {
						@Override
						public void onPSubscribe(String pattern, int subscribedChannels) {
							System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
						}

						@Override
						public void onPMessage(String pattern, String channel, String message) {
							System.out.print("[Pattern:" + pattern + "]");
							System.out.print("[Channel: " + channel + "]");
							System.out.println("[Message: " + message + "]");
						}
					};

		Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //停止订阅
            jedisPubSub.punsubscribe();
        };
//		new Thread(runnable).start();

		//订阅
		jedis.psubscribe(jedisPubSub, "__keyspace@*__:RTnotify", "__keyspace@*__:RTnotify1");
		System.out.println("end");
	}
}
