package com.test.demo.stream;

import java.util.Arrays;
import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 * Created on 2018/1/8.
 */
public class ReductionDemo {

	public static void main(String[] args) {
		testCollect();
		testReduce();
		testWordCount();
	}

	public static void testCollect() {
		List<Integer> roster = Arrays.asList(1,2,3,4,5);

		Averager averageCollect = roster.stream()
										.collect(Averager::new, Averager::accept, Averager::combine);

		System.out.println("Average age of male members: " + averageCollect.average());
	}

	public static void testReduce() {
		List<Integer> roster = Arrays.asList(1,2,3,4,5);
		Integer totalAgeReduce = roster
				.stream()
				.reduce(
						0,
						(a, b) -> a + b
				);
		System.out.println("totalAgeReduce:" + totalAgeReduce);
	}

	public static void testWordCount() {
		List<String> words = Arrays.asList("a", "a", "b", "a", "a", "b");

		WordCounter countCollect = words.stream()
										.collect(WordCounter::new,
												WordCounter::accept,
												WordCounter::combine);

		System.out.println("word count：" + countCollect.getWordCountMap());
	}
}
