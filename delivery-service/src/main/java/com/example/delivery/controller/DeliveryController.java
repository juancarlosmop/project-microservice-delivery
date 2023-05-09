package com.example.delivery.controller;

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
import com.example.commons.dto.RpBase;
import com.example.delivery.client.IUserClient;
import com.example.delivery.dto.RqDelivery;
import com.example.delivery.model.DeliveryEntity;
import com.example.delivery.service.IDeliveryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	private IUserClient userClient;
	
	@Autowired
	private IDeliveryService deliveryService;
	@GetMapping("/test")
	public String testValue() {
		
		return "hello from delivery";
	}
	
	@GetMapping("/testUser")
	public String testUser() {
		
		return userClient.testValue();
	}
	
	/*
	 * Method to create a new Delivery
	 * 
	 * @param RqDelivery information
	 * @return ResponseEntity response of the operation
	 * */
	@PostMapping("/create-bill-delivery")
	public ResponseEntity<RpBase> createBillDelivery(@Valid @RequestBody RqDelivery rqDelivery){
		RpBase rp=new RpBase();
		rp.setCode("OK");
		rp.setMessage("delivery was created");
		log.info("Starting the creation of Delivery");
		deliveryService.createDelivery(rqDelivery);
		log.info("End the creation of Delivery");
		return new ResponseEntity<>(rp,HttpStatus.CREATED);
	}
	
	/*
	 * Method to get a delivery by id
	 * 
	 * @param id_delivery
	 * @return ResponseEntity response of the operation
	 * */
	@GetMapping("/get-delivery/{id_delivery}")
	public DeliveryEntity getDelivery(@PathVariable("id_delivery") int idDelivery) {
		log.info("Start to get delivery");
		return deliveryService.getByIdDelivery(idDelivery);
	}

}
