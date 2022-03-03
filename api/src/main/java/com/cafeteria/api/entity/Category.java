package com.cafeteria.api.entity;

import java.util.List;

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

@Entity(name="category")
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CategoryId")
	private Integer categoryId;
	
	@Column(name="CategoryName", nullable = false, length= 100)
	private String categoryName;
	
	@Column(name="CategoryImage", nullable = false, length= 100)
	private String categoryImage;
	
	//----references----

	@Column(name="StoreId")
	private Integer storeId;
	
	@JsonBackReference(value="categoryList")
	@ManyToOne
	@JoinColumn(name="StoreId", insertable=false, updatable=false)
	private Store store;
	
	@OneToMany(targetEntity=Beverage.class, mappedBy="category", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	List<Beverage> beverageList;

	//----getter setter----
	public List<Beverage> getBeverageList() {
		return beverageList;
	}

	public void setBeverageList(List<Beverage> beverageList) {
		this.beverageList = beverageList;
	}
	
	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
}
