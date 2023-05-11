package com.example.delivery.service;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.delivery.client.IProductClient;
import com.example.delivery.dto.RqDelivery;
import com.example.delivery.model.DeliveryItemEntity;
import com.example.delivery.model.DeliveryEntity;
import com.example.delivery.repository.IDeliveryItemRepository;
import com.example.delivery.repository.IDeliveryRepository;
import com.example.commons.model.ProductEntity;
import com.example.commons.model.UserEntity;
@Service
public class DeliveryService implements IDeliveryService{
	
	private final RedissonClient redissonClient;
	private final IDeliveryRepository deliveryRepository;
	private final IProductClient productClient;
	private final IDeliveryItemRepository deliveryItemRepository;
	public DeliveryService(RedissonClient redissonClient,IDeliveryRepository deliveryRepository,IProductClient productClient,IDeliveryItemRepository deliveryItemRepository) {
        this.redissonClient = redissonClient;
        this.deliveryRepository = deliveryRepository;
        this.productClient = productClient;
        this.deliveryItemRepository = deliveryItemRepository;
    }
	
	/**
	 * (no-javadoc)
	 * @see com.example.delivery.service.DeliveryService#createDelivery( com.example.delivery.dto.RqDelivery)
	 */
	@Override
	public void createDelivery(RqDelivery delivery) {
		RBucket<UserEntity> bucket = redissonClient.getBucket("user:1");
		UserEntity user = bucket.get();
		DeliveryEntity deliveryEntity = new DeliveryEntity();
		deliveryEntity.setDateDeliver(new Date());
		deliveryEntity.setDeliveryHour(delivery.getHour());
		DeliveryEntity newDelivery=deliveryRepository.save(deliveryEntity);
		delivery.getDeliveryItems().forEach(deliv->{
			DeliveryItemEntity delivItem = new DeliveryItemEntity();
			delivItem.setDelivery(newDelivery);
			delivItem.setUser(user);
			ProductEntity product =productClient.getProductById( deliv.getIdProduct());
			delivItem.setProduct(product);
			
			deliveryItemRepository.save(delivItem);
		});
		
	}
	
	/**
	 * (no-javadoc)
	 * @see com.example.delivery.service.DeliveryService#getByIdDelivery(int id)
	 */
	@Override
	public DeliveryEntity getByIdDelivery(int id) {
		Optional<DeliveryEntity> delivery=deliveryRepository.findById(id);
		return delivery.get();
	}

}
