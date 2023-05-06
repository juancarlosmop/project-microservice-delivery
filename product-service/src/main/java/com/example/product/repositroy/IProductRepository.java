package com.example.product.repositroy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.commons.model.ProductEntity;
/*
 * Interface to manage the operations entity ProductEntity
 * 
 * @author JC
 * */
@Repository
public interface IProductRepository extends CrudRepository<ProductEntity,Integer> {

}
