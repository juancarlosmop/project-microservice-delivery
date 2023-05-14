package com.example.users.test.controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.commons.dto.UserDto;
import com.example.commons.model.UserEntity;
import com.example.users.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import com.example.commons.util.CommonUtil;
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@Test
	public void createUserTest() throws Exception {
		// Given
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
       //when
        userService.createUser(userDto);
        
      //then
        mockMvc.perform(MockMvcRequestBuilders.post("/user/create-user/").contentType(MediaType.APPLICATION_JSON)
				.content(CommonUtil.convertOjectToJson(userDto))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("the user was created")); 
        verify(userService, times(1)).createUser(userDto);
	}
	
	@Test
	public void getUserById() throws Exception {
		// Given
		UserEntity user = new UserEntity();
		user.setIdUser(1);
		user.setName("juan");
		user.setLastName("moreno");
		user.setEmail("juan@gmail.com");
       //when
        when(userService.getUserById(1)).thenReturn(user);
        
      //then
        mockMvc.perform(MockMvcRequestBuilders.get("/user/get-user/{id_user}",1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.idUser").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("juan"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("moreno"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("juan@gmail.com"));
		
	}
	
	@Test
	public void getAllUsers() throws Exception {
		// Given
		List<UserEntity> lsUser = new ArrayList<UserEntity>();
		UserEntity user = new UserEntity();
		user.setIdUser(1);
		user.setName("juan");
		user.setLastName("moreno");
		user.setEmail("juan@gmail.com");
		lsUser.add(user);
		UserEntity user2 = new UserEntity();
		user2.setIdUser(2);
		user2.setName("juan2");
		user2.setLastName("moreno2");
		user2.setEmail("juan@gmail.com2");
		lsUser.add(user2);
		
       //when
        when(userService.getAllUsers()).thenReturn(lsUser);
        
      //then
        mockMvc.perform(MockMvcRequestBuilders.get("/user/get-users/"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].idUser").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("juan"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value("moreno"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].email").value("juan@gmail.com"));
		
	}
	
}
