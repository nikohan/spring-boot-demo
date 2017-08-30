package com.test.demo.concurrency;

/**
 * 非线程安全
 * @see java concurrency in practice : code list 3-1
 * Created by nikohan on 2017/8/26.
 */
public class NoVisibility {
	private static boolean ready;
	private static int number;

	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}

	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}
