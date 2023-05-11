package com.example.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.commons.model.ProductEntity;
import com.example.delivery.exception.ResourceNotFoundException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import com.example.delivery.exception.FeignErrorDecoder;

@FeignClient(name="product-service")
public interface IProductClient {
	
	@GetMapping("/product/get-product/{id_product}")
	@Retry(name="productServiceCB")
	@CircuitBreaker(name="productServiceCB", fallbackMethod="getProductFallback")
	public ProductEntity getProductById(@PathVariable("id_product") int idProduct);
	
	
	default ProductEntity getProductFallback(int idProduct,Throwable exception) {
		ProductEntity product = new ProductEntity();
		System.out.println("Exception class=" + exception.getClass().getName());
		System.out.println("Exception took place: " + exception.getMessage());
		if ( product.getIdProduct() == 0) {
			throw new ResourceNotFoundException("Product not found");
		}
		return product;
	}

}
