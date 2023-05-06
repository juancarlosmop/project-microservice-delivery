package com.example.delivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.delivery.model.DeliveryItemEntity;

/*
 * Interface to manage the operations entity DeliveryItemEntity
 * 
 * @author JC
 * */
@Repository
public interface IDeliveryItemRepository extends CrudRepository<DeliveryItemEntity,Integer> {

}
