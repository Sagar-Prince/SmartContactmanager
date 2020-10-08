package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.smart.entity.User;
@Component
public interface UserRepositery extends JpaRepository<User, Integer> {

}
