package com.cafeteria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Employee;
import com.cafeteria.api.service.EmployeeService;


@RestController
@RequestMapping("api/v1/employee")
@org.springframework.web.bind.annotation.CrossOrigin("http://localhost:4200")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	//----basic api-----
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer id) {
		return employeeService.getEmployeeById(id);
	}
	
	@DeleteMapping("{id}")
	public int deleteEmployeeById(@PathVariable("id") Integer id) {
		return employeeService.deleteEmployeeById(id);
	}
	
	@PutMapping
	public Employee editEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	//-----extended api------
	@GetMapping
	public Employee getEmployeeByLoginName(@RequestBody Employee employee) {
		return employeeService.getEmployeeByLoginName(employee.getEmployeeLoginName());
	}
	
	@GetMapping("/store/{storeId}")
	public List<Employee> getEmployeeByLoginName(@PathVariable("storeId") Integer storeId) {
		return employeeService.getEmployeesByStoreId(storeId);
	}
}
