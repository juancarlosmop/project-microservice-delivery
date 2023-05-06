package com.example.product.controller;

import java.util.List;

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
import com.example.commons.dto.ProductDto;
import com.example.product.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired 
	private IProductService productService;
	@GetMapping("/test")
	public String testValue() {
		
		return "hello from produc";
	}
	
	/*
	 * Method to create a new Product
	 * 
	 * @param ProductDto information
	 * @return ResponseEntity response of the operation
	 * */

	@PostMapping("/create-product")
	public ResponseEntity<RpBase> createUser(@Valid @RequestBody ProductDto rpProduct) {
		RpBase rp = new RpBase();
		rp.setMessage("the product was created");
		rp.setCode("OK");
		productService.createProduct(rpProduct);
		return new ResponseEntity<>(rp,HttpStatus.CREATED);
	}
	
	/*
	 * Method to get a product by id
	 * 
	 * @param id_product
	 * @return ResponseEntity response of the operation
	 * */

	@GetMapping("/get-product/{id_product}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id_product") int idProduct) {
		ProductDto user=productService.getProductById(idProduct);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	/*
	 * Method to get all Products
	 * 
	 * @return ResponseEntity response of the operation
	 * */

	@GetMapping("/get-products")
	public ResponseEntity<List<ProductDto>> getAllUsers() {
		List<ProductDto> lsuser=productService.getAllProducts();
		return new ResponseEntity<>(lsuser,HttpStatus.OK);
	}
	
}
