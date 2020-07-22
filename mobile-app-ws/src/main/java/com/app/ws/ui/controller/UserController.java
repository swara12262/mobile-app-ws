package com.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ws.service.UserService;
import com.app.ws.shared.dto.UserDto;
import com.app.ws.ui.model.reponse.UserRest;
import com.app.ws.ui.model.request.UserDetailsRequestModel;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	UserService userService;

	@GetMapping
	public String getUser() {
		return "getUser was called";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue=new UserRest();
		
		//copy properties received in request object in UserDto class object
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createUser= userService.createUser(userDto);//will return value after saving our input to database
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
	}

	@PutMapping
	public String updateUser() {
		return "Update User called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "Delete User called";
	}
}
