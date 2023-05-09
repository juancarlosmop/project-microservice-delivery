package com.example.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.commons.dto.ProductDto;
import com.example.commons.model.ProductEntity;
import com.example.product.repositroy.IProductRepository;
@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductRepository productRepository;
	/**
	 * (no-javadoc)
	 * @see com.example.product.service.ProductService#createProduct( com.example.product.dto.ProductDto)
	 */
	@Override
	public void createProduct(ProductDto rpProduct) {
		ProductEntity product = new ProductEntity();
		product.setNameProduct(rpProduct.getNameProduct());
		product.setPrice(rpProduct.getPrice());
		product.setQuantity(rpProduct.getQuantity());
		productRepository.save(product);
		
	}
	
	/**
	 * (no-javadoc)
	 * @see com.example.product.service.ProductService#getProductById(int id)
	 */
	@Override
	public ProductEntity getProductById(int id) {
		Optional<ProductEntity> product= productRepository.findById(id);
		return product.get();
	}
	
	/**
	 * (no-javadoc)
	 * @see com.example.product.service.ProductService#getAllProducts( )
	 */
	@Override
	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> lspEntity = (List<ProductEntity>) productRepository.findAll();
		return lspEntity;
	}
	
}
