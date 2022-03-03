package com.cafeteria.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="employee")
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EmployeeId")
	private Integer employeeId;
	
	@Column(name="EmployeeLoginName", nullable = false, unique= true, length= 100)
	private String employeeLoginName;
	
	@Column(name="EmployeePassword", nullable = false, length= 100)
	private String employeePassword;
	
	@Column(name="EmployeeName", nullable = false, length= 100)
	private String employeeName;
	
	@Column(name="EmployeeGender", nullable = false)
	private Integer employeeGender;
	
	@Column(name="EmployeeRole", nullable = false)
	private Integer employeeRole;
	
	@Column(name="EmployeeEmail", nullable = false, length= 100)
	private String employeeEmail;
	
	@Column(name="EmployeePhone", nullable = false, length= 100)
	private String employeePhone;
	
	@Column(name="EmployeeImage", nullable = false, length= 1000)
	private String employeeImage;

	//----- references -------
	@Column(name="StoreId")
	private Integer storeId;

	@JsonBackReference(value="employeeList")
	@ManyToOne
	@JoinColumn(name="StoreId", insertable= false, updatable= false)
	private Store store;
	
	
	//------getter setter------
	public Store getStore() {
		return store;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeLoginName() {
		return employeeLoginName;
	}

	public void setEmployeeLoginName(String employeeLoginName) {
		this.employeeLoginName = employeeLoginName;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(Integer employeeGender) {
		this.employeeGender = employeeGender;
	}

	public Integer getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(Integer employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeImage() {
		return employeeImage;
	}

	public void setEmployeeImage(String employeeImage) {
		this.employeeImage = employeeImage;
	}
}
