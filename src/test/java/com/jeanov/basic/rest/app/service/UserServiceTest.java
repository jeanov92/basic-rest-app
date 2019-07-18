package com.jeanov.basic.rest.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jeanov.basic.rest.app.bean.UserBean;
import com.jeanov.basic.rest.app.entity.User;
import com.jeanov.basic.rest.app.exception.NotFoundException;
import com.jeanov.basic.rest.app.repository.UserRepository;
import com.jeanov.basic.rest.app.service.UserService;

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
		assertThrows(NotFoundException.class, () -> userService.getUser(1));
	}

}
