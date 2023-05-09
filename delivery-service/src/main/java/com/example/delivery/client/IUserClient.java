package com.example.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.commons.model.UserEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name="user-service")
public interface IUserClient {
	
	@GetMapping("/user/test")
	@Retry(name="userCB")
	@CircuitBreaker(name="userCB", fallbackMethod="getUserTestFallback")
	public String testValue();
	
	@GetMapping("/user/get-user/{id_user}")
	//@Retry(name="userServiceCB")
	//@CircuitBreaker(name="userServiceCB", fallbackMethod="getUserFallback")
	public UserEntity getUserById(@PathVariable("id_user") int idUser);
	
	/*default String getUserTestFallback(Throwable exception) {
		System.out.println("Exception class=" + exception.getClass().getName());
		System.out.println("Exception took place: " + exception.getMessage());
		return "something happen";
	}*/
	
	default String getUserFallback(Throwable exception) {
		System.out.println("Exception class=" + exception.getClass().getName());
		System.out.println("Exception took place: " + exception.getMessage());
		return "something happen";
	}
}
