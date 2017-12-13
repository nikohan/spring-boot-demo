package com.test.demo.redis;

/**
 * Created on 2017/12/12.
 */
public class RealTimeRedisMain {
	public static void main(String[] args) throws InterruptedException {
		RealTimeRedis realTimeRedis = RealTimeRedis.newInstance("192.168.30.104", 6379, 100, 3);
		realTimeRedis.put("test", "123");
		while (true) {
			Thread.sleep(1000);
			System.out.println(realTimeRedis.get("test"));
//			realTimeRedis.stop();
		}
	}
}
