package com.example.users.service;

import java.util.List;


import com.example.commons.dto.UserDto;
import com.example.commons.model.UserEntity;

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
	public UserEntity getUserById(int id);
	
	/*
	 * Method to get all records from User
	 * 
	 * 
	 * */
	public List<UserEntity> getAllUsers();
	

}
