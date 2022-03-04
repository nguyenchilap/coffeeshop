package com.cafeteria.api.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="voucher")
@Table(name="voucher")
public class Voucher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VoucherId", nullable = false)
	private Integer voucherId;
	
	@Column(name="VoucherCode", nullable = false, length= 100)
	private String voucherCode;
	
	@Column(name="VoucherStartDate", nullable = false)
	private Date voucherStartDate;
	
	@Column(name="VoucherEndDate", nullable = false)
	private Date voucherEndDate;
	
	@Column(name="VoucherPercentage", nullable = false)
	private Float voucherPercentage;
	
	@Column(name="VoucherMax", nullable = false)
	private Float voucherMax;
	
	@Column(name="VoucherMinOrder", nullable = false)
	private Float voucherMinOrder;
	
	@Column(name="VoucherLimit", nullable = false)
	private Integer voucherLimit;
	
	@Transient
	@OneToMany(targetEntity=Order.class, mappedBy="voucher", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	Set<Order> orderList;
	
	//-----getter and setter----

	public Set<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}
	
	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public Date getVoucherStartDate() {
		return voucherStartDate;
	}

	public void setVoucherStartDate(Date voucherStartDate) {
		this.voucherStartDate = voucherStartDate;
	}

	public Date getVoucherEndDate() {
		return voucherEndDate;
	}

	public void setVoucherEndDate(Date voucherEndDate) {
		this.voucherEndDate = voucherEndDate;
	}

	public Float getVoucherPercentage() {
		return voucherPercentage;
	}

	public void setVoucherPercentage(Float voucherPercentage) {
		this.voucherPercentage = voucherPercentage;
	}

	public Float getVoucherMax() {
		return voucherMax;
	}

	public void setVoucherMax(Float voucherMax) {
		this.voucherMax = voucherMax;
	}

	public Float getVoucherMinOrder() {
		return voucherMinOrder;
	}

	public void setVoucherMinOrder(Float voucherMinOrder) {
		this.voucherMinOrder = voucherMinOrder;
	}

	public Integer getVoucherLimit() {
		return voucherLimit;
	}

	public void setVoucherLimit(Integer voucherLimit) {
		this.voucherLimit = voucherLimit;
	}
	
	
}
