package com.example.delivery.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="delivery")
public class DeliveryEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_delivery")
	private int idDelivery;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
	@Column(name="date_delivery")
	private Date dateDeliver;
	private String hour;
	@OneToMany(mappedBy="delivery")
	private List<DeliveryItemEntity> deliveryItems;
	

}
