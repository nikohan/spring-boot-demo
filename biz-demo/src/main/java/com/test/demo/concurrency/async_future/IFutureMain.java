package com.test.demo.concurrency.async_future;

/**
 * Created on 2017/12/13.
 */
public class IFutureMain {
	public static void main(String[] args) {
		new IFutureMain()
				.add(3 * 1000, 1, 2)
				.addListener(new IFutureListener<Integer>() {
					@Override
					public void onCompleted(IFuture<Integer> future) throws Exception {
						System.out.println(future.getNow());
					}
				});
	}

	/**
	 * 延迟加
	 * @param delay 延时时长 milliseconds
	 * @param a 加数
	 * @param b 加数
	 * @return 异步结果
	 */
	public DelayAdditionFuture add(long delay, int a, int b) {
		DelayAdditionFuture future = new DelayAdditionFuture();
		new Thread(new DelayAdditionTask(delay, a, b, future)).start();
		return future;
	}

	private class DelayAdditionTask implements Runnable {

		private long delay;

		private int a, b;

		private DelayAdditionFuture future;

		public DelayAdditionTask(long delay, int a, int b, DelayAdditionFuture future) {
			super();
			this.delay = delay;
			this.a = a;
			this.b = b;
			this.future = future;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(delay);
				Integer i = a + b;
				// 这里设置future为完成状态(正常执行完毕)
				future.setSuccess(i);
			} catch (InterruptedException e) {
				// 这里设置future为完成状态(异常执行完毕)
				future.setFailure(e.getCause());
			}
		}

	}
}


class DelayAdditionFuture extends AbstractFuture<Integer> {

	@Override
	public IFuture<Integer> setSuccess(Object result) {
		return super.setSuccess(result);
	}

	@Override
	public IFuture<Integer> setFailure(Throwable cause) {
		return super.setFailure(cause);
	}

}