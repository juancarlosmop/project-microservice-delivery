package com.example.product.service;

import java.util.List;


import com.example.commons.dto.ProductDto;
import com.example.commons.model.ProductEntity;
public interface IProductService {
	/*
	 * Method to create a new Product
	 * @param ProductDto
	 * 
	 * */
	public void createProduct(ProductDto rpUser);
	
	/*
	 * Method to get a record from Product
	 * @param int id
	 * 
	 * */
	public ProductEntity getProductById(int id);
	
	/*
	 * Method to get all records from Product
	 * 
	 * 
	 * */
	public List<ProductEntity> getAllProducts();
}
