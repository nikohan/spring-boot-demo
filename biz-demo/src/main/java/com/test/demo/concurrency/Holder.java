package com.test.demo.concurrency;

/**
 * java concurrency in practice : code list 3-15
 * https://www.zhihu.com/question/40072199/answer/116975888
 * https://stackoverflow.com/questions/40144528/how-to-understand-an-example-of-book-java-concurrency-in-practice
 * Created by nikohan on 2017/8/26.
 */
public class Holder {
	private int n;

	public Holder(int n) {
		this.n = n;
	}

	public void assertSanity() {
		//获取两个n正好位于缓存写入主内存的之前和之后
		if (n != n) {
			throw new AssertionError("this statement is false.");
		}
	}
}
