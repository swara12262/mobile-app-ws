package com.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String id);
}
