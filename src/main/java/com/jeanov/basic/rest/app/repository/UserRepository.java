package com.jeanov.basic.rest.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanov.basic.rest.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
