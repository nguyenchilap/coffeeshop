package com.cafeteria.api.entity.EmbeddedKey;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.cafeteria.api.entity.Beverage;
import com.cafeteria.api.entity.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class OrderKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3973622653313650366L;
	
	@JsonBackReference(value="order")
	@ManyToOne(cascade = CascadeType.ALL)
	private Order order;
	
	@JsonBackReference(value="beverage")
	@ManyToOne(cascade = CascadeType.ALL)
    private Beverage beverage;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}   
	
}
