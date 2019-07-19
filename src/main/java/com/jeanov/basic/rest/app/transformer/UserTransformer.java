package com.jeanov.basic.rest.app.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jeanov.basic.rest.app.dto.UserDTO;
import com.jeanov.basic.rest.app.entity.User;

public class UserTransformer {

	public static UserDTO userToUserBean(Optional<User> userOpt) {
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			UserDTO userBean = new UserDTO();
			userBean.setUserId(user.getUserId());
			userBean.setName(user.getName());
			userBean.setAge(user.getAge());
			return userBean;
		} else {
			return null;
		}
	}

	public static User userBeanToUser(UserDTO userBean) {
		User user = new User();
		user.setUserId(userBean.getUserId());
		user.setName(userBean.getName());
		user.setAge(userBean.getAge());
		return user;
	}

	public static List<UserDTO> usersToUsersBean(List<User> users) {
		List<UserDTO> usersBean = new ArrayList<UserDTO>();
		users.forEach(user -> {
			UserDTO userBean = new UserDTO();
			userBean.setUserId(user.getUserId());
			userBean.setName(user.getName());
			userBean.setAge(user.getAge());
			usersBean.add(userBean);
		});
		return usersBean;
	}

}
