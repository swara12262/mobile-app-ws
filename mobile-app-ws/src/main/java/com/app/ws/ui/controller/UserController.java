package com.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ws.exceptions.UserServiceException;
import com.app.ws.service.UserService;
import com.app.ws.shared.dto.UserDto;
import com.app.ws.ui.model.reponse.ErrorMessages;
import com.app.ws.ui.model.reponse.UserRest;
import com.app.ws.ui.model.request.UserDetailsRequestModel;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping(path= "/{id}", produces= { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		
		UserRest returnValue=new UserRest();
		
		UserDto userDto= userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	@PostMapping(consumes={ MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces= { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)throws Exception
	{
		UserRest returnValue=new UserRest();
		//check for required fields
		if(userDetails.getFirstName().isEmpty())throw new NullPointerException("Test: Object is null");
		
		//copy properties received in request object in UserDto class object
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createUser= userService.createUser(userDto);//will return value after saving our input to database
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
	}

	@PutMapping(path= "/{id}",consumes={ MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces= { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue=new UserRest();
		
			if(userDetails.getFirstName().isEmpty())throw new NullPointerException("Test: Object is null");
		//copy properties received in request object in UserDto class object
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updateUser= userService.updateUser(id, userDto);//will return value after saving our input to database
		BeanUtils.copyProperties(updateUser, returnValue);
		
		return returnValue;
	}

	@DeleteMapping
	public String deleteUser() {
		return "Delete User called";
	}
}
