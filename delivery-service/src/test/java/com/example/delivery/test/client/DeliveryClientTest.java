package com.example.delivery.test.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.delivery.client.IProductClient;
import com.example.commons.model.ProductEntity;
@SpringBootTest
public class DeliveryClientTest {

	@Autowired
    private IProductClient productClient;
	
	@Test
	public void testGetProduct() {
		int idProduct=1;
		ProductEntity product = productClient.getProductById(idProduct);
		assertThat(product).isNotNull();
		assertEquals(idProduct,product.getIdProduct());
	}
}
