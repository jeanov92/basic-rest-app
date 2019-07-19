package com.jeanov.basic.rest.app.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeanov.basic.rest.app.dto.UserDTO;
import com.jeanov.basic.rest.app.entity.User;
import com.jeanov.basic.rest.app.service.UserService;
import com.jeanov.basic.rest.app.transformer.UserTransformer;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(produces = { "application/json" }, path = "/users")
	public List<Resource<UserDTO>> getUsers() {
		List<User> users = userService.getUsers();
		List<UserDTO> usersDTO = UserTransformer.usersToUsersBean(users);
		return createResourcesFromUsersDTO(usersDTO);
	}

	// Applying HATEOAS Hypermedia as the engine of application state
	@GetMapping(produces = { "application/json" }, path = "/user/{id}")
	public Resource<UserDTO> getUser(@PathVariable int id) {
		UserDTO userDTO = userService.getUser(id);
		Link link = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();
		return new Resource<UserDTO>(userDTO, link);
	}

	@PostMapping(path = "/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userBean) {
		User user = userService.saveUser(userBean);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("Deleted: " + id, HttpStatus.OK);
	}

	private List<Resource<UserDTO>> createResourcesFromUsersDTO(List<UserDTO> usersDTO) {
		List<Resource<UserDTO>> resources = new ArrayList<>();
		usersDTO.forEach(userDTO -> {
			Link link = linkTo(methodOn(UserController.class, userDTO.getUserId())
					.getUser(userDTO.getUserId()))
					.withSelfRel();
			resources.add(new Resource<UserDTO>(userDTO, link));
		});
		return resources;
	}
}
