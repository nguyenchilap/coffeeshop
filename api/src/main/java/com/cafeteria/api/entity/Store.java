package com.cafeteria.api.entity;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="store")
@NoArgsConstructor
@AllArgsConstructor
@Table(name="store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="StoreId")
	private Integer storeId;
	
	@Column(name="StoreName", nullable = false, length= 100)
	private String storeName;
	
	@Column(name="StoreAddress", nullable = false, length= 100)
	private String storeAddress;
	
	@Column(name="StoreImage", length= 1000)
	private String storeImage;
	
	@JsonManagedReference(value="employeeList")
	@OneToMany(targetEntity=Employee.class, mappedBy="store", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	Set<Employee> employeeList;
	
	@JsonManagedReference(value="categoryList")
	@OneToMany(targetEntity=Category.class, mappedBy="store", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	Set<Category> categoryList;
	
	
	//----------setter & getter---------------
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreImage() {
		return storeImage;
	}

	public void setStoreImage(String storeImage) {
		this.storeImage = storeImage;
	}
	
	public Set<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(Set<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Set<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
