package com.cafeteria.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.Employee;
import com.cafeteria.api.repository.EmployeeRepository;
import com.cafeteria.api.repository.StoreRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepo;
	private StoreRepository storeRepo;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepo, StoreRepository storeRepo) {
		this.employeeRepo = employeeRepo;
		this.storeRepo = storeRepo;
	}

	public Employee createEmployee(Employee e) {
		e.setStore(storeRepo.findById(e.getStoreId()).orElse(null));
		e.setEmployeePassword(new BCryptPasswordEncoder().encode(e.getEmployeePassword()));
		return employeeRepo.save(e);
	}
	
	public Employee getEmployeeById(Integer id) {
		return employeeRepo.findById(id).orElse(null);
	}
	
	public Employee getEmployeeByLoginName(String loginName) {
		return employeeRepo.getEmployeeByLoginName(loginName);
	}
	
	public List<Employee> getAllEmployee(){
		return employeeRepo.findAll();
	}
	
	public boolean deleteEmployeeById(Integer id) {
		Employee e = employeeRepo.findById(id).orElse(null);
		if (e != null) {
			employeeRepo.deleteById(id);
			return true;
		} else return false;
	}
	
	public void deleteEmployeeByIds(List<Integer> ids) {
		this.employeeRepo.deleteAllById(ids);
	}
	
	public Employee updateEmployee(Employee employee) {
		Employee e = employeeRepo.findById(employee.getEmployeeId()).orElse(null);
		e.setEmployeeEmail(employee.getEmployeeEmail());
		e.setEmployeeGender(employee.getEmployeeGender());
		e.setEmployeeName(employee.getEmployeeName());
		e.setEmployeePhone(employee.getEmployeePhone());
		e.setEmployeeImage(employee.getEmployeeImage());
		return employeeRepo.save(e);
	}
	
	public Employee changeImage(Integer id, String image) {
		Employee e = employeeRepo.getById(id);
		e.setEmployeeImage(image);
		return employeeRepo.save(e);
	}
	
	public Employee changePassword(Integer id, String password) {
		Employee e = employeeRepo.findById(id).orElse(null);
		e.setEmployeePassword(password);
		return employeeRepo.save(e);
	}
	
	public List<Employee> getEmployeesByStoreId(Integer storeId) {
		return employeeRepo.getEmployeesByStoreId(storeId);
	}
}
