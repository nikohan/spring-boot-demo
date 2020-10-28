package com.test.demo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author zhaohan
 * @date 2020/10/28
 */
public interface HelloService {

	@GET("message/count")
	Call<Object> hello();
}
