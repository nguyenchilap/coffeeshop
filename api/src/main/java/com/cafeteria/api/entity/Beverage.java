package com.cafeteria.api.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="beverage")
@Table(name="beverage")
public class Beverage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BeverageId", nullable = false)
	private Integer beverageId;
	
	@Column(name="BeverageSize", nullable = false)
	private Integer beverageSize;
	
	@Column(name="BeveragePrice", nullable = false)
	private Integer beveragePrice;
	
	@JsonBackReference(value="orderBeverages")
	@OneToMany(mappedBy="primaryKey.beverage", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<OrderBeverage> orderBeverages = new HashSet<OrderBeverage>();
	
	public Set<OrderBeverage> getOrderBeverages() {
		return orderBeverages;
	}
	
	//----- references -------
	@Column(name="CategoryId")
	private Integer categoryId;

	@JsonBackReference(value="category")
	@ManyToOne
	@JoinColumn(name="CategoryId", insertable= false, updatable= false)
	private Category category;

	//------getter setter------
	public void setOrderBeverages(Set<OrderBeverage> orderBeverages) {
		this.orderBeverages = orderBeverages;
	}

	public void addBeverage(OrderBeverage beverage) {
		this.orderBeverages.add(beverage);
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getBeverageId() {
		return beverageId;
	}

	public void setBeverageId(Integer beverageId) {
		this.beverageId = beverageId;
	}

	public Integer getBeverageSize() {
		return beverageSize;
	}

	public void setBeverageSize(Integer beverageSize) {
		this.beverageSize = beverageSize;
	}

	public Integer getBeveragePrice() {
		return beveragePrice;
	}

	public void setBeveragePrice(Integer beveragePrice) {
		this.beveragePrice = beveragePrice;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
