package com.test.demo.algorithms.sort;

import java.util.Arrays;

/**
 * 快速排序
 * https://www.cnblogs.com/coderising/p/5708801.html
 * Created on 2017/12/14.
 */
public class FastSort {

	public static void main(String[] args) {
		int[] array = new int[] {3, 2, 4, 1, 5, 9, 6, 7, 8};
		System.out.println(Arrays.toString(array));
		sort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array, int lo , int hi) {
		if(lo >= hi) {
			return;
		}
		int index = partition(array, lo, hi);
		sort(array, lo,index - 1);
		sort(array,index + 1, hi);
	}

	public static int partition(int[] array, int lo, int hi) {
		//固定的切分方式
		int key = array[lo];

		while(lo < hi) {
			//从后半部分向前扫描
			while(array[hi] >= key && hi > lo) {
				hi --;
			}
			array[lo] = array[hi];

			//从前半部分向后扫描
			while(array[lo] <= key && hi > lo) {
				lo ++;
			}
			array[hi] = array[lo];
		}

		array[hi] = key;
		return hi;
	}
}
