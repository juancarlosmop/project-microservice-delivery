package com.example.product.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.product.repositroy.IProductRepository;
import com.example.product.service.ProductService;
import com.example.commons.dto.ProductDto;
import com.example.commons.model.ProductEntity;

@SpringBootTest
public class ProductRepositoryTest {
	@Mock
	private IProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	public void createProductTest() {
		// give
		ProductEntity product = new ProductEntity();
		product.setIdProduct(1);
		product.setNameProduct("coca");
		product.setPrice(100);
		product.setQuantity(10);

		ProductDto productDto = new ProductDto();
		product.setIdProduct(1);
		product.setNameProduct("coca");
		product.setPrice(100);
		product.setQuantity(10);
		when(productRepository.save(product)).thenReturn(product);
		// when
		productService.createProduct(productDto);

		// then
		verify(productRepository).save(any(ProductEntity.class));
	}

	@Test
	public void getProductByIdTest() {
		// given
		ProductEntity proct1 = new ProductEntity();
		proct1.setIdProduct(1);
		proct1.setNameProduct("coca");
		proct1.setPrice(100);
		proct1.setQuantity(10);
		when(productRepository.findById(1)).thenReturn(Optional.of(proct1));

		// when
		ProductEntity returnProduct=productService.getProductById(1);
		
		//then
		verify(productRepository).findById(1);
		assertEquals(proct1,returnProduct);
	}

	@Test
	public void getAllProductsTest() {
		// Given
		List<ProductEntity> expectedProductLs = new ArrayList<>();
		ProductEntity proct1 = new ProductEntity();
		proct1.setIdProduct(1);
		proct1.setNameProduct("coca");
		proct1.setPrice(100);
		proct1.setQuantity(10);
		ProductEntity proct2 = new ProductEntity();
		proct2.setIdProduct(1);
		proct2.setNameProduct("papas");
		proct2.setPrice(100);
		proct2.setQuantity(10);
		expectedProductLs.add(proct1);
		expectedProductLs.add(proct2);
		when(productRepository.findAll()).thenReturn(expectedProductLs);
		// when
		List<ProductEntity> ls = productService.getAllProducts();

		assertEquals(expectedProductLs.size(), ls.size());

	}
}
