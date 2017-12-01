package com.test.demo.algorithms;

/**
 * 0<i,j,k<100,(i,j,k)互不相同，三个数与顺序无关，即(1,2,97)和(2,1,97)视为一组，i + j + k = 100,总共多少组(i,j,k)
 * Created on 2017/11/30.
 */
public class TotalGroupCalculator {

	public static int calGroupNum(int limit) {
		int count = 0;

		for(int i = 1; i < limit - 1; i ++) {
			for(int j = i + 1; j < limit; j ++) {
				//方式1，三层循环
//				for (int k = j + 1; k <= limit; k ++) {
//					if ((limit - (i + j)) == k) {
//						count++;
//						System.out.println("i=" + i + ",j=" + j + ",k=" + k);
//					}
//				}

				//方式2，两层循环
				int k = limit - (i + j);
				if (k <= 0) {
					//k小于等于0不符合，再进行第二层循环没有意义
					break;
				}
				if (k > j && k <= limit) {
					count++;
					System.out.println("i=" + i + ",j=" + j + ",k=" + k);
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		System.out.println(calGroupNum(100));
	}
}
