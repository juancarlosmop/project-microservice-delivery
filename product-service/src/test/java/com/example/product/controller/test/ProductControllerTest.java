package com.example.product.controller.test;

import static org.mockito.Mockito.when;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.product.service.ProductService;
import com.example.commons.model.ProductEntity;
import com.example.commons.dto.ProductDto;
import com.example.commons.util.CommonUtil;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productSerevice;

	@Test
	public void createProduct() throws Exception {
		// give
		ProductDto product = new ProductDto();
		product.setIdProduct(1);
		product.setNameProduct("cocacola");
		product.setPrice(100);
		product.setQuantity(10);
		// when
		productSerevice.createProduct(product);

		// then
		mockMvc.perform(MockMvcRequestBuilders.post("/product/create-product/").contentType(MediaType.APPLICATION_JSON)
				.content((CommonUtil.convertOjectToJson(product))))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("the product was created"));
	}

	@Test
	public void getProductById() throws Exception {
		// give
		ProductEntity product = new ProductEntity();
		product.setIdProduct(1);
		product.setNameProduct("cocacola");
		product.setPrice(100);
		product.setQuantity(10);
		// when
		when(productSerevice.getProductById(1)).thenReturn(product);
		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/product/get-product/{id_product}", 1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.idProduct").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nameProduct").value("cocacola"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100))
				.andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10));
	}

	@Test
	public void getAllProducts() throws Exception {
		// give
		ProductEntity product1 = new ProductEntity();
		product1.setIdProduct(1);
		product1.setNameProduct("cocacola");
		product1.setPrice(100);
		product1.setQuantity(10);
		
		ProductEntity product2 = new ProductEntity();
		product2.setIdProduct(1);
		product2.setNameProduct("papas");
		product2.setPrice(200);
		product2.setQuantity(10);
		List<ProductEntity> productLs = new ArrayList<ProductEntity>();
		productLs.add(product1);
		productLs.add(product2);
		//when
		when(productSerevice.getAllProducts()).thenReturn(productLs);
		//then
		mockMvc.perform(MockMvcRequestBuilders.get("/product/get-products/"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].idProduct").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameProduct").value("cocacola"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value(100))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].quantity").value(10));
	}

}
