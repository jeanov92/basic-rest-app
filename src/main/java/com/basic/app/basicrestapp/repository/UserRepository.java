package com.basic.app.basicrestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basic.app.basicrestapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
