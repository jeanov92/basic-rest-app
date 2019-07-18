package com.jeanov.basic.rest.app.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserBean {

	@NotNull
	private int id;

	@NotNull
	private String name;

	@NotNull
	@Max(120)
	@Min(10)
	private int age;

	public UserBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
