package com.cafeteria.api.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cafeteria.api.entity.EmbeddedKey.OrderKey;

@Entity(name="order_beverage")
@Table(name="order_beverage")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.order",
        joinColumns = @JoinColumn(name = "OrderId")),
    @AssociationOverride(name = "primaryKey.beverage",
        joinColumns = @JoinColumn(name = "BeverageId")) })
public class OrderBeverage {
	
	@EmbeddedId
	private OrderKey primaryKey = new OrderKey();
	
	@Column(name="Quantity")
	private Integer quantity;
	
	
	public OrderKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(OrderKey id) {
		this.primaryKey = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setOrder(Order order) {
		this.getPrimaryKey().setOrder(order);
	}

	@Transient
	public Beverage getBeverage() {
		return getPrimaryKey().getBeverage();
	}

	public void setBeverage(Beverage beverage) {
		this.getPrimaryKey().setBeverage(beverage);
	}
	
}
