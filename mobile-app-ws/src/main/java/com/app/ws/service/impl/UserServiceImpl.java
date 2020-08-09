package com.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.ws.io.entity.UserEntity;
import com.app.ws.io.repositories.UserRepository;
import com.app.ws.service.UserService;
import com.app.ws.shared.Utils;
import com.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	Utils utils;
	
	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		if(userRepository.findByEmail(user.getEmail())!=null) 
			throw new RuntimeException("Record already exists");
		
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.generateUserId(30));
			
		UserEntity storeUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(storeUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity  = userRepository.findByEmail(username);
		if(userEntity==null) throw new UsernameNotFoundException("Email id : "+username +"Does not exists in Database");
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity  = userRepository.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException("Email id : "+email +"Does not exists in Database");
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue );
		
		
		return returnValue;
	}

}
