package com.test.demo.v2.route;

import com.test.demo.v2.action.UserAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created on 2018/1/8.
 */
@Configuration
public class UserRoute {
	private UserAction userAction;

	public UserRoute(UserAction userAction) {
		this.userAction = userAction;
	}

	@Bean
	public RouterFunction<?> routerFunction() {
		return route(GET("/api/user").and(accept(MediaType.APPLICATION_JSON)), userAction::handleGetUsers)
				.and(route(GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userAction::handleGetUserById));
	}
}
