package com.example.delivery.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.delivery.client.IProductClient;
import com.example.delivery.client.IUserClient;
import com.example.delivery.dto.RqDelivery;
import com.example.delivery.model.DeliveryItemEntity;
import com.example.delivery.model.DeliveryEntity;
import com.example.delivery.repository.IDeliveryItemRepository;
import com.example.delivery.repository.IDeliveryRepository;
import com.example.commons.dto.UserDto;
import com.example.commons.model.ProductEntity;
import com.example.commons.model.UserEntity;
import com.example.commons.dto.ProductDto;
@Service
public class DeliveryService implements IDeliveryService{
	
	@Autowired
	private IDeliveryRepository deliveryRepository;
	@Autowired
	private IDeliveryItemRepository deliveryItemRepository;
	
	@Autowired
	private IUserClient userClient;
	
	@Autowired
	private IProductClient productClient;
	
	
	/**
	 * (no-javadoc)
	 * @see com.example.delivery.service.DeliveryService#createDelivery( com.example.delivery.dto.RqDelivery)
	 */
	@Override
	public void createDelivery(RqDelivery delivery) {
		ModelMapper mapper = new ModelMapper();
		DeliveryEntity deliveryEntity = new DeliveryEntity();
		deliveryEntity.setDateDeliver(delivery.getDateDeliver());
		deliveryEntity.setHour(delivery.getHour());
		DeliveryEntity newDelivery=deliveryRepository.save(deliveryEntity);
		delivery.getDeliveryItems().forEach(deliv->{
			DeliveryItemEntity delivItem = new DeliveryItemEntity();
			delivItem.setDelivery(newDelivery);
			UserDto user=userClient.getUserById( deliv.getIdUser() );
			delivItem.setUser(mapper.map(user, UserEntity.class));
			ProductDto product =productClient.getProductById( deliv.getIdProduct());
			delivItem.setProduct(mapper.map(product, ProductEntity.class));
			
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
