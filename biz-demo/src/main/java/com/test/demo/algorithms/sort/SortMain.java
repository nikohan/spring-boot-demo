package com.test.demo.algorithms.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created on 2018/1/10.
 */
public class SortMain {
	public static void main(String[] args) {
		int n = 100000000;
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = randomInt(n);
		}

		System.out.println("testParallelStream:" + testParallelStream(nums));
		System.out.println("testStream:" + testStream(nums));
		System.out.println("testCollections:" + testCollections(nums));
		System.out.println("testBubbleSort:" + testBubbleSort(nums));
		System.out.println("testFastSort:" + testFastSort(nums));
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
