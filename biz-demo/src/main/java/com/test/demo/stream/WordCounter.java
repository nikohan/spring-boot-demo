package com.test.demo.stream;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/1/8.
 */
public class WordCounter implements StringConsumer {
	private Map<String, Integer> wordCountMap = new HashMap<>();

	public Map<String, Integer> getWordCountMap() {
		return wordCountMap;
	}

	@Override
	public void accept(String s) {
		putAndAdd(s, 1);
	}

	public void combine(WordCounter other) {
		Map<String, Integer> otherCountMap = other.getWordCountMap();
		for (Map.Entry<String, Integer> entry : otherCountMap.entrySet()) {
			putAndAdd(entry.getKey(), entry.getValue());
		}
	}

	private void putAndAdd(String s, Integer integer) {
		Integer count = wordCountMap.get(s);
		if (count == null) {
			wordCountMap.put(s, 1);
			return;
		}

		wordCountMap.put(s, count + integer);
	}
}
