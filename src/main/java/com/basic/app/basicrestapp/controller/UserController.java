package com.basic.app.basicrestapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.basic.app.basicrestapp.bean.UserBean;
import com.basic.app.basicrestapp.entity.User;
import com.basic.app.basicrestapp.exception.NotFoundException;
import com.basic.app.basicrestapp.service.UserService;
import com.basic.app.basicrestapp.transformer.UserTransformer;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/users")
	public List<UserBean> getUsers() {
		List<User> users = userService.getUsers();
		return UserTransformer.usersToUsersBean(users);
	}

	@GetMapping(path = "/user/{id}")
	public UserBean getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@PostMapping(path = "/user")
	public ResponseEntity createUser(@Valid @RequestBody UserBean userBean) {
		User user = userService.saveUser(userBean);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/user/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
	}
}
