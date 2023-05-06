package com.example.users.service;

import java.util.List;


import com.example.commons.dto.UserDto;

public interface IUserService {
	/*
	 * Method to create a new User
	 * @param Client data from User
	 * 
	 * */
	public void createUser(UserDto rpUser);
	
	/*
	 * Method to get a record from User
	 * @param int id
	 * 
	 * */
	public UserDto getUserById(int id);
	
	/*
	 * Method to get all records from User
	 * 
	 * 
	 * */
	public List<UserDto> getAllUsers();
	

}
