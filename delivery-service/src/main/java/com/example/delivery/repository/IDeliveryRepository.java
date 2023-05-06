package com.example.delivery.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.delivery.model.DeliveryEntity;
/*
 * Interface to manage the operations entity DeliveryEntity
 * 
 * @author JC
 * */
@Repository
public interface IDeliveryRepository extends CrudRepository<DeliveryEntity,Integer>{

}
