package com.example.users.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.commons.dto.UserDto;
import com.example.commons.model.UserEntity;
import com.example.users.repository.IUserRepository;


@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;
	
	/**
	 * (no-javadoc)
	 * @see com.example.users.service.UserService#createUser( com.example.users.dto.RpUser)
	 */
	@Override
	public void createUser(UserDto rpUser) {
		UserEntity user = new UserEntity();
		user.setName( rpUser.getName());
		user.setLastName( rpUser.getLastName());
		user.setEmail(rpUser.getEmail());
		userRepository.save(user);
		
	}
	
	/**
	 * (no-javadoc)
	 * @see com.example.users.service.UserService#getUserById(int id)
	 */
	@Override
	public UserEntity getUserById(int id) {
		Optional<UserEntity> user=userRepository.findById(id);
		return user.get();
	}
	
	/**
	 * (no-javadoc)
	 * @see com.example.users.service.UserService#getAllUsers()
	 */
	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> lsUser = (List<UserEntity>) userRepository.findAll();
		return lsUser;
	}

}
