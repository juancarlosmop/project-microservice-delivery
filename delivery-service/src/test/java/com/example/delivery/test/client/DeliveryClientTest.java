package com.example.delivery.test.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.delivery.client.IProductClient;
import com.example.commons.model.ProductEntity;
@SpringBootTest
public class DeliveryClientTest {

	@Mock
    private IProductClient productClient;
	
	@Test
	public void testGetProduct() {
		//give
		int idProduct=1;
		ProductEntity expectedProduct = new ProductEntity();
	    expectedProduct.setIdProduct(idProduct);
	    when(productClient.getProductById(idProduct)).thenReturn(expectedProduct);
		//when
		ProductEntity product = productClient.getProductById(idProduct);
		//then
		assertEquals(idProduct,product.getIdProduct());
	}
	@Test
	public void getProductClientException() {
		//when
		when(productClient.getProductFallback(anyInt(), any(Throwable.class))).thenCallRealMethod();
		//then
		assertThrows(RuntimeException.class,()->{
			productClient.getProductFallback(1, new Throwable("Test Error"));
		});
	}
}
