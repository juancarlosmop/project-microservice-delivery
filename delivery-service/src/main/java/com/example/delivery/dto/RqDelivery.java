package com.example.delivery.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RqDelivery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
	private Date dateDeliver;
	private String hour;
	@JsonProperty("delivery_items")
	private List<RqDeliveryItem> deliveryItems;
}
