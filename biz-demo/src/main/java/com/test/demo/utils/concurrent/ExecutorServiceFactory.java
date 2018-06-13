package com.test.demo.utils.concurrent;

import java.util.concurrent.ExecutorService;

/**
 * Created by zhaohan on 2018/6/13.
 */
public interface ExecutorServiceFactory {

	/**
	 * 创建线程池服务对象.
	 *
	 * @return 线程池服务对象
	 */
	ExecutorService create();
}
