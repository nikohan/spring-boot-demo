package com.test.demo.stream;

import com.test.demo.algorithms.sort.BubbleSort;
import com.test.demo.algorithms.sort.FastSort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

/**
 * https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 * Created on 2018/1/8.
 */
public class ReductionDemo {

	public static void main(String[] args) {
//		testCollect();
//		testReduce();
//		testWordCount();

		int n = 100000000;
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = randomInt(n);
		}

		System.out.println("testParallelStream:" + testParallelStream(nums));
		System.out.println("testStream:" + testStream(nums));
//		System.out.println("testCollections:" + testCollections(nums));
//		System.out.println("testBubbleSort:" + testBubbleSort(nums));
//		System.out.println("testFastSort:" + testFastSort(nums));
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
		List<String> words = Arrays.asList("a", "a", "b", "a", "a", "b", "ad", "ad", "ad", "c");

		Map<String, Integer> countMap = new HashMap<>();
		countMap.put("c", 2);

		WordCounter countCollect = words.stream()
										.collect(() -> new WordCounter(countMap),
												WordCounter::accept,
												WordCounter::combine);

		System.out.println("word count：" + countCollect.getWordCountMap());
	}

	public static long testParallelStream(int[] nums) {
		return testTime(nums,
				list -> Arrays.stream(list).parallel().sorted().forEach(value -> {}));
	}

	public static long testStream(int[] nums) {
		return testTime(nums,
				list -> Arrays.stream(list).sorted().forEach(value -> {}));
	}

	public static long testCollections(int[] nums) {
		return testTime(nums, Arrays::sort);
	}

	public static long testBubbleSort(int[] nums) {
		return testTime(nums, BubbleSort::sort);
	}

	public static long testFastSort(int[] nums) {
		return testTime(nums, FastSort::sort);
	}


	private static int randomInt(int max) {
		Random random = new Random();
		return random.nextInt(max);
	}

	private static void display(int[] nums) {
		Arrays.stream(nums).forEach(i -> System.out.print(i + ","));
		System.out.println();
	}

	private static int[] ofCopy(int[] nums) {
		int[] copyNums = new int[nums.length];
		System.arraycopy(nums, 0, copyNums, 0, nums.length);
		return copyNums;
	}

	private static long testTime(int[] nums, Consumer<int[]> c) {
		int[] copyNums = ofCopy(nums);
		long start = System.currentTimeMillis();
		c.accept(copyNums);
		long end = System.currentTimeMillis();
		return end - start;
	}
}
