package com.test.demo.v2.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/1/8.
 */
@RestController
public class DemoAction {

	@GetMapping("/")
	public String home() {
		return "Hello World!";
	}
}
