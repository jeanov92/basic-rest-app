package com.basic.app.basicrestapp.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.basic.app.basicrestapp.bean.UserBean;
import com.basic.app.basicrestapp.entity.User;

public class UserTransformer {

	public static UserBean userToUserBean(Optional<User> userOpt) {
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			UserBean userBean = new UserBean();
			userBean.setId(user.getId());
			userBean.setName(user.getName());
			userBean.setAge(user.getAge());
			return userBean;
		} else {
			return null;
		}
	}

	public static User userBeanToUser(UserBean userBean) {
		User user = new User();
		user.setId(userBean.getId());
		user.setName(userBean.getName());
		user.setAge(userBean.getAge());
		return user;
	}

	public static List<UserBean> usersToUsersBean(List<User> users) {
		List<UserBean> usersBean = new ArrayList<UserBean>();
		users.forEach(user -> {
			UserBean userBean = new UserBean();
			userBean.setId(user.getId());
			userBean.setName(user.getName());
			userBean.setAge(user.getAge());
			usersBean.add(userBean);
		});
		return usersBean;
	}

}
