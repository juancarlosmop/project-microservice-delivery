package com.example.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.commons.dto.ProductDto;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name="product-service")
public interface IProductClient {
	
	@GetMapping("/product/get-product/{id_product}")
	@Retry(name="productServiceCB")
	@CircuitBreaker(name="productServiceCB", fallbackMethod="getProductFallback")
	public ProductDto getProductById(@PathVariable("id_product") int idProduct);
	
	
	default String getProductFallback(Throwable exception) {
		System.out.println("Exception class=" + exception.getClass().getName());
		System.out.println("Exception took place: " + exception.getMessage());
		return "something happen";
	}

}
