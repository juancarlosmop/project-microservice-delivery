package com.example.delivery.test.repository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.delivery.repository.IDeliveryItemRepository;
import com.example.delivery.repository.IDeliveryRepository;
import com.example.delivery.service.DeliveryService;
import com.example.delivery.client.IProductClient;
import com.example.delivery.dto.RqDelivery;
import com.example.delivery.dto.RqDeliveryItem;
import com.example.delivery.model.DeliveryEntity;
import com.example.delivery.model.DeliveryItemEntity;
import java.util.List;
import java.util.Optional;

import com.example.commons.model.UserEntity;
import com.example.commons.model.ProductEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
@SpringBootTest
public class DeliveryServiceRespositoryTest {
	@Mock
    private RBucket<Object> bucket;
    
    @Mock
    private RedissonClient redissonClient;

    @Mock
    private IDeliveryRepository deliveryRepository;

    @Mock
    private IDeliveryItemRepository deliveryItemRepository;

    @Mock
    private IProductClient productClient;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    public void testCreateDelivery() {
        // Given
        RqDelivery rqDelivery = new RqDelivery();
        rqDelivery.setHour("12:00");
        RqDeliveryItem deliveryItemRequest = new  RqDeliveryItem();
        deliveryItemRequest.setIdProduct(1);
        rqDelivery.setDeliveryItems(Collections.singletonList(deliveryItemRequest));
        
        UserEntity user = new UserEntity();
        user.setIdUser(1);
        user.setName("Juan");
        user.setLastName("Perez");
        user.setEmail("juan.perez@example.com");

        DeliveryEntity savedDelivery = new DeliveryEntity();
        savedDelivery.setIdDelivery(1);
        savedDelivery.setDateDeliver(new Date());
        savedDelivery.setDeliveryHour(rqDelivery.getHour());
        savedDelivery.setDeliveryItems(Collections.emptyList());

        DeliveryItemEntity savedDeliveryItem = new DeliveryItemEntity();
        savedDeliveryItem.setDelivery(savedDelivery);
        savedDeliveryItem.setUser(user);
        savedDeliveryItem.setProduct(new ProductEntity());

        ProductEntity product = new ProductEntity();
        product.setIdProduct(1);
        product.setNameProduct("Product 1");
        product.setPrice(12.0);
        when(redissonClient.getBucket("user:1")).thenReturn(bucket);
        when(bucket.get()).thenReturn(user);
        when(productClient.getProductById(1)).thenReturn(product);
        when(deliveryRepository.save(any(DeliveryEntity.class))).thenReturn(savedDelivery);
        when(deliveryItemRepository.save(any(DeliveryItemEntity.class))).thenReturn(savedDeliveryItem);

        // When
        deliveryService.createDelivery(rqDelivery);

        // Then
        verify(redissonClient).getBucket("user:1");
        verify(bucket).get();
        verify(deliveryRepository).save(any(DeliveryEntity.class));
        verify(deliveryItemRepository).save(any(DeliveryItemEntity.class));
        verify(productClient).getProductById(1);
    }
    
    @Test
    public void  getDeliveryTest() {
    	// Given
        DeliveryEntity delivery = new DeliveryEntity();
        delivery.setIdDelivery(1);
        delivery.setDeliveryHour("12:00");
        DeliveryItemEntity deliveryItemRequest = new  DeliveryItemEntity();
        deliveryItemRequest.setProduct(new ProductEntity());
        delivery.setDeliveryItems(Collections.singletonList(deliveryItemRequest));
        when(deliveryRepository.findById(1)).thenReturn(Optional.of(delivery));
        //when
        DeliveryEntity deliveget=deliveryService.getByIdDelivery(1);
        
        //then
        assertThat(deliveget).isNotNull();
        assertEquals(delivery,deliveget);
        verify(deliveryRepository).findById(1);
    }
}
