package com.test.demo.nowcoder;

/**
 * https://www.nowcoder.com/ta/coding-interviews
 * nowcoder offer 1:
 * 	在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 	请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 思路：
 * 	先判断整数是否大于第一行最后一个数，如果大于，查找下一行。如果小于，在此行查找是否存在这个整数
 *
 * Created by zhaohan on 2019/5/8.
 */
public class Solution1 {

	public static void main(String[] args) {
		int target = 4;
		int [][] array = {
				{1,2,3},
				{2,4,6},
				{4,7,9},
		};

		System.out.println("结果：" + find(target, array));
	}

	public static boolean find(int target, int [][] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		for (int[] row : array) {
			if (row == null || row.length == 0) {
				continue;
			}

			int rowLen = row.length;
			if (target <= row[rowLen - 1]) {
				for (int num : row) {
					if (num == target) {
						//存在
						return true;
					}
				}
			}
		}

		return false;
	}
}
