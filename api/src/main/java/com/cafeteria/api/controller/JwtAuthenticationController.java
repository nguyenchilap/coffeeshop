package com.cafeteria.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.config.security.JwtResponse;
import com.cafeteria.api.config.security.JwtUtils;
import com.cafeteria.api.entity.Employee;
import com.cafeteria.api.service.EmployeeService;
import com.cafeteria.api.service.MyUserDetailsService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8000"})
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager; 
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	@Autowired
	private JwtUtils jwtTokenUtil;
	
	public JwtAuthenticationController(AuthenticationManager authManager, MyUserDetailsService userDetailsService) {
		super();
		this.authManager = authManager;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/login") 
	public ResponseEntity<?> createAuthToken(@RequestBody Employee _employee) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(_employee.getEmployeeLoginName(), _employee.getEmployeePassword()));
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(_employee.getEmployeeLoginName());
		final Employee employee = employeeService.getEmployeeByLoginName(_employee.getEmployeeLoginName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token, employee.getEmployeeId(), employee.getEmployeeLoginName(), employee.getEmployeeName(),
				employee.getEmployeeEmail(), employee.getEmployeePhone(), employee.getEmployeeRole(), employee.getEmployeeImage(), employee.getEmployeeGender()));
	}
}
