package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.smart.entity.User;
@Component
public interface UserRepositery extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.Email = :Email")
	public User getUserByUserName(@Param("Email") String Email);
	
}
