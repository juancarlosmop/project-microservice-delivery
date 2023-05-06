package com.example.delivery.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RqDeliveryItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("id_user")
	private int idUser;
	@JsonProperty("id_product")
	private int idProduct;
	@JsonProperty("id_delivery")
	private int idDelivery;
}
