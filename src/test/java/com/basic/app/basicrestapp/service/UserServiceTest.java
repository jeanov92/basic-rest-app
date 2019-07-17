package com.basic.app.basicrestapp.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import com.basic.app.basicrestapp.bean.UserBean;
import com.basic.app.basicrestapp.entity.User;
import com.basic.app.basicrestapp.exception.NotFoundException;
import com.basic.app.basicrestapp.repository.UserRepository;

public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUserSuccess() {
		User user = new User();
		user.setId(1);
		user.setName("Marcelo Vieira");
		user.setAge(33);
		Optional<User> userOpt = Optional.of(user);

		when(userRepository.findById(Mockito.anyInt())).thenReturn(userOpt);

		UserBean userResult = userService.getUser(1);

		assertEquals(1, userResult.getId());
		assertEquals(33, userResult.getAge());
		assertEquals("Marcelo Vieira", userResult.getName());
	}

	@Test
	public void testGetUserNotFound() {
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

		UserBean userBean = null;
		try {
			userBean = userService.getUser(1);
		} catch (Exception e) {
			boolean isNotFoundException = e instanceof NotFoundException;
			assertEquals(true, isNotFoundException);
		}
	}

}
