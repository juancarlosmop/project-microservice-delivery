package com.example.users.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.service.IUserService;
import com.example.commons.dto.RpBase;
import com.example.commons.dto.UserDto;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private  IUserService userService;
	@GetMapping("/test")
	public String testValue() {
		
		return "hello from users";
	}
	
	/*
	 * Method to create a new user
	 * 
	 * @param RpUser information
	 * @return ResponseEntity response of the operation
	 * */

	@PostMapping("/create-user")
	public ResponseEntity<RpBase> createUser(@Valid @RequestBody UserDto rpUser) {
		RpBase rp = new RpBase();
		rp.setMessage("the user was created");
		rp.setCode("OK");
		userService.createUser(rpUser);
		return new ResponseEntity<>(rp,HttpStatus.CREATED);
	}
	
	/*
	 * Method to get a user by id
	 * 
	 * @param id_user
	 * @return ResponseEntity response of the operation
	 * */

	@GetMapping("/get-user/{id_user}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id_user") int idUser) {
		UserDto user=userService.getUserById(idUser);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	/*
	 * Method to get all users
	 * 
	 * @return ResponseEntity response of the operation
	 * */

	@GetMapping("/get-users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> lsuser=userService.getAllUsers();
		return new ResponseEntity<>(lsuser,HttpStatus.OK);
	}

}
