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

	@Autowired
    private IProductClient productClient;
	
	@Test
	public void testGetProduct() {
		//give
		int idProduct=1;
		//when
		ProductEntity product = productClient.getProductById(idProduct);
		//then
		assertThat(product).isNotNull();
		assertEquals(idProduct,product.getIdProduct());
	}
	/*@Test
	public void getProductClientException() {
		//when
		when(productClient.getProductFallback(anyInt(), any(Throwable.class))).thenCallRealMethod();
		
		assertThrows(RuntimeException.class,()->{
			productClient.getProductFallback(1, new Throwable("Test Error"));
		});
	}*/
}
