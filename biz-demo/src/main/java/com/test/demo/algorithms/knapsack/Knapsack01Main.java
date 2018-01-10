package com.test.demo.algorithms.knapsack;

/**
 * 0-1背包问题
 * http://www.hawstein.com/posts/dp-knapsack.html
 * http://blog.csdn.net/crayondeng/article/details/15784093
 * Created on 2018/1/10.
 */
public class Knapsack01Main {

	private static Commodity[] goods = new Commodity[] {
			new Commodity(0, 0),
			new Commodity(60, 10),
			new Commodity(100, 20),
			new Commodity(120, 30)
	};

	//物品个数
	private static final int N = goods.length - 1;
	//背包容量
	private static final int W = 50;

	private static int[][] select = new int[N + 1][W + 1];

	public static void main(String[] args) {
		int maxvalue = maxValue();
		System.out.println("The max value is: " + maxvalue);

		int remainSpace = W;
		//输出所选择的物品列表：
		for(int i = N; i >= 1; i--) {
			if (remainSpace >= goods[i].getWeight()) {
				if ((select[i][remainSpace] - select[i - 1][remainSpace - goods[i].getWeight()] == goods[i].getValue())) {
					System.out.println("item " + i + " is selected");

					//如果第i个物品被选择，那么背包剩余容量将减去第i个物品的重量 ;
					remainSpace = remainSpace - goods[i].getWeight();
				}
			}
		}
	}

	public static int maxValue() {
		//初始没有物品时候，背包的价值为0
		for (int w = 1; w <= W; w++) {
			select[0][w] = 0;
		}

		for (int i = 1; i <= N; i++) {
			//背包容量为0时，最大价值为0
			select[i][0] = 0;

			for (int w = 1; w <= W; w++) {
				if (goods[i].getWeight() <= w) {
					//当前物品i的重量小于等于w，进行选择
					select[i][w] = max(goods[i].getValue() + select[i - 1][w - goods[i].getWeight()], select[i - 1][w]);
				} else {
					//当前物品i的重量大于w，不选择
					select[i][w] = select[i - 1][w];
				}
			}
		}

		return select[N][W];
	}

	private static int max(int a, int b) {
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}
}
