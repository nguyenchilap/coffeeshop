package com.cafeteria.api.entity;

import java.sql.Date;
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

@Entity(name="orders")
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OrderId", nullable = false)
	private Integer orderId;
	
	@Column(name="OrderDate", nullable = false)
	private Date orderDate;
	
	@Column(name="OrderNote", length=1000)
	private String orderNote;
	
	@Column(name="OrderAddress", nullable = false, length=100)
	private String orderAddress;
	
	@Column(name="OrderTotal")
	private Integer orderTotal;
	
	@Column(name="OrderFinalTotal")
	private Float orderFinalTotal;
	
	//-------- references ----------
	@Column(name="VoucherId")
	private Integer voucherId;

	@ManyToOne
	@JoinColumn(name="VoucherId", insertable= false, updatable= false)
	private Voucher voucher;
	
	@OneToMany(mappedBy="primaryKey.order", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<OrderBeverage> orderBeverages = new HashSet<OrderBeverage>();
	
	public void addBeverage(OrderBeverage beverage) {
		this.orderBeverages.add(beverage);
	}
	
	//-------- getter setter --------
	
	public Set<OrderBeverage> getOrderBeverages() {
		return orderBeverages;
	}

	public void setOrderBeverages(Set<OrderBeverage> orderBeverages) {
		this.orderBeverages = orderBeverages;
	}

	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	
	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Float getOrderFinalTotal() {
		return orderFinalTotal;
	}

	public void setOrderFinalTotal(Float orderFinalTotal) {
		this.orderFinalTotal = orderFinalTotal;
	}

	/*public Set<Beverage> getOrderBeverages() {
		return orderBeverages;
	}

	public void setOrderBeverages(Set<Beverage> orderBeverages) {
		this.orderBeverages = orderBeverages;
	}*/
	
}
