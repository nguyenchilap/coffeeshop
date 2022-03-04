package com.cafeteria.api.config.security;

public class JwtResponse {
	
	private final String tokenId;
	
	private Integer employeeId;
	
	private String employeeLoginName;
	
	private String employeeName;
	
	private String employeeEmail;
	
	private String employeePhone;
	
	private Integer employeeRole;

	private String employeeImage;
	
	private Integer employeeGender;
	
	public JwtResponse(String tokenId, Integer employeeId, String employeeLoginName, String employeeName,
			String employeeEmail, String employeePhone, Integer employeeRole, String employeeImage, Integer employeeGender) {
		this.tokenId = tokenId;
		this.employeeId = employeeId;
		this.employeeLoginName = employeeLoginName;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeePhone = employeePhone;
		this.employeeRole = employeeRole;
		this.employeeImage = employeeImage;
		this.employeeGender = employeeGender;
	}
	
	
	public String getTokenId() {
		return tokenId;
	}

	public Integer getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(Integer employeeGender) {
		this.employeeGender = employeeGender;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public Integer getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(Integer employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getEmployeeImage() {
		return employeeImage;
	}

	public void setEmployeeImage(String employeeImage) {
		this.employeeImage = employeeImage;
	}
	
	public JwtResponse(String tokenId) {
		this.tokenId = tokenId;
	}
	
	public String getJwt() {
		return this.tokenId;
	}
}
