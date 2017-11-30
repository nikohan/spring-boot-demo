package com.test.demo.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2017/11/25.
 */
public class HashMapDemo {
	public static void main(String[] args) {
		//table[] 的大小总是2的幂次方，即如果传入是17，数组大小就是32
		Map<String, String> map = new HashMap<>(17);

		//table[] 的初始化会在第一次put时执行
		map.put("1","1");
		System.out.println(map);
	}
}
