package com.example.delivery.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import com.example.commons.model.ProductEntity;
import com.example.commons.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Getter
@Setter
@Entity(name="delivery_items")
public class DeliveryItemEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_delivery_item")
	private int idDeliveryItem;
	@ManyToOne
	@JoinColumn(name="id_user")
	private UserEntity user;
	@ManyToOne
	@JoinColumn(name="id_product")
	private ProductEntity product;
	@ManyToOne
	@JoinColumn(name="id_delivery")
	@JsonIgnoreProperties({"deliveryItems"})
	private DeliveryEntity delivery;
	
}
