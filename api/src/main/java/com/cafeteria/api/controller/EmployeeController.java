package com.cafeteria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Employee;
import com.cafeteria.api.entity.ResponseObject;
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
	public ResponseEntity<ResponseObject> addEmployee(@RequestBody Employee employee) {
		Employee checkExistingLoginName = employeeService.getEmployeeByLoginName(employee.getEmployeeLoginName());
		if (checkExistingLoginName != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject("failed", "Login name existed !", null));
		} else {
			Employee empl = employeeService.createEmployee(employee);
			if (empl != null) {
				return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", empl));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create employee !", null));
			}
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllEmployees(){
		List<Employee> empls = employeeService.getAllEmployee();
		if (empls.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", empls));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No employee found !", null));
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseObject> getEmployeeById(@PathVariable Integer id) {
		Employee empl = employeeService.getEmployeeById(id);
		if (empl != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", empl));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Employee with id " + id + " doesn't exist!", null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseObject> deleteEmployeeById(@PathVariable Integer id) {
		boolean deleteComplete = employeeService.deleteEmployeeById(id);
		if (deleteComplete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteComplete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Employee with id " + id + " doesn't exist!", null));
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseObject> editEmployee(@RequestBody Employee employee) {
		Employee empl = employeeService.updateEmployee(employee);
		if (empl != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", empl));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot edit employee !", null));
		}
	}
	
	//-----extended api------
	
	@GetMapping
	public ResponseEntity<ResponseObject> getEmployeeByLoginName(@RequestBody Employee employee) {
		Employee empl = employeeService.getEmployeeByLoginName(employee.getEmployeeLoginName());
		if (empl != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", empl));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Employee with login name " + employee.getEmployeeLoginName() + " doesn't exist!", null));
		}
	}
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<ResponseObject> getEmployeesByStoreId(@PathVariable Integer storeId) {
		List<Employee> empls = employeeService.getEmployeesByStoreId(storeId);
		if (empls.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", empls));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No employee found !", null));
		}
	}
}
