package com.test.demo.v2.action;

import com.test.demo.v2.dao.UserDao;
import com.test.demo.v2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created on 2018/1/8.
 */
@RestController
public class UserAction {

	@Autowired
	private UserDao userDao;

//	@GetMapping("/api/user")
	public Mono<ServerResponse> handleGetUsers(ServerRequest request) {
		return ServerResponse.ok().body(userDao.getUsers(), User.class);
	}

//	@GetMapping("/api/user/{id}")
	public Mono<ServerResponse> handleGetUserById(ServerRequest request) {
		return userDao.getUserById(request.pathVariable("id"))
					  .flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
					  .switchIfEmpty(ServerResponse.notFound().build());
	}
}
