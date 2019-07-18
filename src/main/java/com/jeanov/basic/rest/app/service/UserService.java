package com.jeanov.basic.rest.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanov.basic.rest.app.bean.UserBean;
import com.jeanov.basic.rest.app.entity.User;
import com.jeanov.basic.rest.app.exception.NotFoundException;
import com.jeanov.basic.rest.app.repository.UserRepository;
import com.jeanov.basic.rest.app.transformer.UserTransformer;

@Component
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public UserBean getUser(int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new NotFoundException("User not found!");

		UserBean userBean = new UserBean();
		BeanUtils.copyProperties(user.get(), userBean);
		return userBean;
	}

	public User saveUser(UserBean userBean) {
		User user = UserTransformer.userBeanToUser(userBean);
		return userRepository.save(user);
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
}
