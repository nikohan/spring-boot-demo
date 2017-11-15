package com.test.demo.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2017/11/15.
 */
public class FastFail {

	public static void list() {
		List<String> strList = new ArrayList<>();
		strList.add("0");
		strList.add("1");
		strList.add("2");

		//throw ConcurrentModificationException
//		for (String str : strList) {
//			strList.remove("0");
//		}

		//throw ConcurrentModificationException
		for (String str : strList) {
			strList.add("0");
		}
	}

	public static void hashMapTest() {
		Map<String, String> map = new HashMap<>();
		map.put("0", "0");
		map.put("1", "1");
		map.put("2", "2");

		//throw ConcurrentModificationException
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey() == "0") {
				map.remove(entry.getKey());
			}
		}
	}

	public static void concurrentHashMapTest() {
		Map<String, String> map = new ConcurrentHashMap<>();
		map.put("0", "0");
		map.put("1", "1");
		map.put("2", "2");
		//throw NullPointerException
//		map.put(null, null);

		//not throw ConcurrentModificationException
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey() == "0") {
				map.remove(entry.getKey());
			}
		}
	}

	public static void main(String[] args) {
//		list();
//		hashMapTest();
		concurrentHashMapTest();
	}
}
