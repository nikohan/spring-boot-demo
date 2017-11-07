package com.test.demo.thread.pipe;

import java.io.PipedOutputStream;

/**
 * Created on 2017/11/7.
 */
public class Sender implements Runnable {

	private PipedOutputStream pos;

	public Sender(){
		this.pos = new PipedOutputStream();
	}

	public PipedOutputStream getPos(){
		return pos;
	}

	@Override
	public void run() {
		String str = "hello pipe stream";
		try {
			this.pos.write(str.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.pos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
