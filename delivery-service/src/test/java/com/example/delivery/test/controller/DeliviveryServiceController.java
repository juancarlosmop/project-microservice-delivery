package com.example.delivery.test.controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import com.example.commons.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.delivery.service.DeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.commons.model.ProductEntity;
import com.example.delivery.dto.RqDelivery;
import com.example.delivery.dto.RqDeliveryItem;
import com.example.delivery.model.DeliveryItemEntity;
import com.example.delivery.model.DeliveryEntity;
import com.example.commons.model.UserEntity;
@SpringBootTest
@AutoConfigureMockMvc
public class DeliviveryServiceController {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DeliveryService deliveryService;
	
	@Test
	public void createDelivery() throws Exception {
		//give
		RqDelivery delivery = new RqDelivery();
		RqDeliveryItem deliveryItemRequest = new  RqDeliveryItem();
	    deliveryItemRequest.setIdProduct(1);
		delivery.setDeliveryItems(Collections.singletonList(deliveryItemRequest));
		//when
		deliveryService.createDelivery(delivery);
		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/delivery/create-bill-deliveryr/").contentType(MediaType.APPLICATION_JSON)
				.content(CommonUtil.convertOjectToJson(delivery))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("delivery was created")); 
        verify(deliveryService, times(1)).createDelivery(delivery);
	}
	
	@Test
	public void getDeliveryById() throws Exception {
		//give
		int idDelivery=1;
		RqDelivery delivery = new RqDelivery();
		RqDeliveryItem deliveryItemRequest = new  RqDeliveryItem();
	    deliveryItemRequest.setIdProduct(1);
	    deliveryItemRequest.setIdUser(1);
		delivery.setDeliveryItems(Collections.singletonList(deliveryItemRequest));
		
		DeliveryEntity deliveryEnt = new DeliveryEntity();
		DeliveryItemEntity deliveryItemRequestEnt = new  DeliveryItemEntity();
		ProductEntity product=new ProductEntity();
		product.setIdProduct(1);
		deliveryItemRequestEnt.setProduct(product);
		UserEntity user = new UserEntity();
		user.setIdUser(1);
		deliveryItemRequestEnt.setUser(user);
		deliveryEnt.setDeliveryItems(Collections.singletonList(deliveryItemRequestEnt));
		//when
		when(deliveryService.getByIdDelivery(idDelivery)).thenReturn(deliveryEnt);
		//then
        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/get-delivery/{id_delivery}",idDelivery))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].deliveryItems.idProduct").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].deliveryItems.idUser").value(1));
		
	}
}
