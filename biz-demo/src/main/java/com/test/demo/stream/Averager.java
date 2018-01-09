package com.test.demo.stream;

import java.util.function.IntConsumer;

/**
 * Created on 2018/1/8.
 */
public class Averager implements IntConsumer {
	private int total = 0;
	private int count = 0;

	@Override
	public void accept(int value) {
		total += value;
		count++;
	}

	public void combine(Averager other) {
		total += other.total;
		count += other.count;
	}

	public double average() {
		return count > 0 ? ((double) total) / count : 0;
	}
}
