package com.test.demo.retrofit;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * http客户端测试
 * https://square.github.io/retrofit/
 * @author zhaohan
 * @date 2020/10/28
 */
public class RetrofitDemo {

	/**
	 * https://apinew.juejin.im/interact_api/v1/message/count
	 */
	public static void main(String[] args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://apinew.juejin.im/interact_api/v1/")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		HelloService service = retrofit.create(HelloService.class);

		Call<Object> repos = service.hello();
		Response<Object> response = repos.execute();
		Object body = response.body();
		System.out.println(body);
	}
}