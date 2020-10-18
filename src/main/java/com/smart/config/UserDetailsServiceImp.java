package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepositery;
import com.smart.entity.User;

public class UserDetailsServiceImp implements UserDetailsService{

	
	@Autowired
	private UserRepositery userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	User user =	userrepository.getUserByUserName(username);
	
	if(user == null) {
		throw new UsernameNotFoundException("Could not find User !!");
	}
		
	
	CustomUserDetails customUserDetails = new CustomUserDetails(user);
		return customUserDetails ;
	}

}
