package com.test.demo.algorithms.knapsack;

/**
 * 物品
 * Created on 2018/1/10.
 */
public class Commodity {
	//价值
	private int value;

	//重量
	private int weight;

	public Commodity(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}
}
