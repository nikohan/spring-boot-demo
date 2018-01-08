package com.test.demo.v2.domain;

/**
 * Created on 2018/1/8.
 */
public class User {

	private Long id;
	private String user;

	public User() {
	}

	public User(Long id, String user) {
		this.id = id;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
