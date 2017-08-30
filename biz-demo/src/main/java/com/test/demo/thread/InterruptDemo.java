package com.test.demo.thread;

/**
 * Created on 2017/8/30.
 */
public class InterruptDemo extends Thread {

	private WaitDemo demo;

	public InterruptDemo(WaitDemo demo) {
		this.demo = demo;
	}

	private static class WaitDemo {
		private volatile boolean bool = false;

		public synchronized void waitAny() throws InterruptedException {
			while (!bool) {
				//进入wait
				wait();
			}
			System.out.println("waitAny done ...");
		}
	}

	@Override
	public void run() {
		try {
			demo.waitAny();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		WaitDemo waitDemo = new WaitDemo();

		InterruptDemo interruptDemo = new InterruptDemo(waitDemo);
		interruptDemo.start();
		System.out.println("interruptDemo started ...");
		Thread.sleep(10000);

		System.out.println("interruptDemo is interrupting..");
		interruptDemo.interrupt();//在interruptDemo进入wait队列时，中断此线程
		System.out.println("interruptDemo is interrupted by [main-Thread]");
	}
}
