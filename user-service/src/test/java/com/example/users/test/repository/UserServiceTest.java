package com.example.users.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.commons.model.UserEntity;
import com.example.users.repository.IUserRepository;
import com.example.users.service.UserService;
import com.example.commons.dto.UserDto;
import org.redisson.api.RBucket;
import java.util.List;
import java.util.ArrayList;
@SpringBootTest
public class UserServiceTest {
	    @Mock
	    private IUserRepository userRepository;
	    
	    @Mock
	    private RedissonClient redissonClient;
	    
	    @InjectMocks
	    private UserService userService;
	    
	    @Test
	    public void createTestUser() {
	        // Given
	        UserDto userDto = new UserDto();
	        userDto.setName("John");
	        userDto.setLastName("Doe");
	        userDto.setEmail("john.doe@example.com");

	        UserEntity savedUser = new UserEntity();
	        savedUser.setIdUser(1);
	        savedUser.setName(userDto.getName());
	        savedUser.setLastName(userDto.getLastName());
	        savedUser.setEmail(userDto.getEmail());

	        when(userRepository.save(savedUser)).thenReturn(savedUser);

	        // When
	        userService.createUser(userDto);

	        // Then
	        assertEquals(userDto.getLastName(), savedUser.getLastName());
	        assertEquals(userDto.getEmail(), savedUser.getEmail());
	    }
	    
	    @Test
	    public void testGetUserById() {
	        UserEntity savedUser = new UserEntity();
	        savedUser.setIdUser(1);
	        savedUser.setName("carlos");
	        savedUser.setLastName("moreno");
	        savedUser.setEmail("jk@gmail.com");

	       
	        when(userRepository.findById(1)).thenReturn(Optional.of(savedUser));

	        
	        RBucket<Object> bucket = mock(RBucket.class);
	        when(redissonClient.getBucket("user:1")).thenReturn(bucket);
	        when(bucket.get()).thenReturn(null,savedUser);

	    
	        UserEntity actualUser1 = userService.getUserById(1);
	        UserEntity actualUser2 = userService.getUserById(1);
	        verify(userRepository).findById(1);
	        verify(redissonClient,times(2)).getBucket("user:1");
	        assertEquals(savedUser, actualUser2);
	    }


	    @Test
	    public void getAllUsersTest() {
	        // Given
	        List<UserEntity> expectedUsers = new ArrayList<>();
	        UserEntity user1 = new UserEntity();
	        user1.setIdUser(1);
	        user1.setName("John");
	        user1.setLastName("Doe");
	        user1.setEmail("john.doe@example.com");
	        UserEntity user2 = new UserEntity();
	        user2.setIdUser(2);
	        user2.setName("Jane");
	        user2.setLastName("Doe");
	        user2.setEmail("jane.doe@example.com");
	        expectedUsers.add(user1);
	        expectedUsers.add(user2);
	        when(userRepository.findAll()).thenReturn(expectedUsers);
	        //when
	        List<UserEntity> ls=userService.getAllUsers();
	        
	       assertEquals(expectedUsers.size(),ls.size());
	        
	    }
}
