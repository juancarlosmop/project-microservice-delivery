package com.example.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.commons.model.UserEntity;

/*
 * Interface to manage the operations entity UserEntity
 * 
 * @author JC
 * */

@Repository
public interface IUserRepository extends CrudRepository<UserEntity,Integer> {

}
