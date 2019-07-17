package com.basic.app.basicrestapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.basic.app.basicrestapp.bean.UserBean;
import com.basic.app.basicrestapp.entity.User;
import com.basic.app.basicrestapp.repository.UserRepository;
import com.basic.app.basicrestapp.transformer.UserTransformer;

@Component
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(int id) {
		return userRepository.findById(id);
	}

	public User saveUser(UserBean userBean) {
		User user = UserTransformer.userBeanToUser(userBean);
		return userRepository.save(user);
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
}
