package com.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ws.ui.model.reponse.UserRest;
import com.app.ws.ui.model.request.UserDetailsRequestModel;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	@GetMapping
	public String getUser() {
		return "getUser was called";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		return null;
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
