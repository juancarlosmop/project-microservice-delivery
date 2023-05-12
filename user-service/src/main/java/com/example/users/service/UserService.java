package com.example.users.service;


import java.util.List;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;



import com.example.commons.dto.UserDto;
import com.example.commons.model.UserEntity;
import com.example.users.repository.IUserRepository;


@Service
public class UserService implements IUserService{
	
	private final RedissonClient redissonClient;
	private final IUserRepository userRepository;
	public UserService(RedissonClient redissonClient,IUserRepository userRepository) {
        this.redissonClient = redissonClient;
        this.userRepository = userRepository;
	}

	
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
			RBucket<UserEntity> bucket = redissonClient.getBucket("user:" + id);
			UserEntity user = bucket.get();
			if (user == null) {
				//if there are not records in Redis
				user=userRepository.findById(id).get();
				if (user != null) {
	                // if the user exist in database
	                bucket.set(user);
	            }
			}
			System.out.print(bucket.get().toString());
			return user;
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
