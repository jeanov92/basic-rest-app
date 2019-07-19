package com.jeanov.basic.rest.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanov.basic.rest.app.dto.UserDTO;
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

	public UserDTO getUser(int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new NotFoundException("User not found!");

		UserDTO userBean = new UserDTO();
		BeanUtils.copyProperties(user.get(), userBean);
		return userBean;
	}

	public User saveUser(UserDTO userBean) {
		User user = UserTransformer.userBeanToUser(userBean);
		return userRepository.save(user);
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
}
