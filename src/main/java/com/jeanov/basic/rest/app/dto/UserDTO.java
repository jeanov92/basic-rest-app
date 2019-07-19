package com.jeanov.basic.rest.app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

public class UserDTO extends ResourceSupport {

	@NotNull
	private int userId;

	@NotNull
	private String name;

	@NotNull
	@Max(120)
	@Min(10)
	private int age;

	public UserDTO() {}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
