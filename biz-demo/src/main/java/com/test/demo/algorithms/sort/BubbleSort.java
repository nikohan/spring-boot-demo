package com.test.demo.algorithms.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * Created on 2017/12/14.
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = new int[] {3, 2, 4, 1, 5, 9, 6, 7, 8};
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr, int start, int end) {
		int tmp;
		for (int i = start; i < end; i ++) {
			for (int j = end - 1; j > i; j --) {
				if (arr[j - 1] > arr[j]) {
					tmp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}

	public static void sort(int[] arr) {
		sort(arr, 0, arr.length);
	}
}
