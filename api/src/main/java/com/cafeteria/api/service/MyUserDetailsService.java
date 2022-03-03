package com.cafeteria.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.Employee;
import com.cafeteria.api.repository.EmployeeRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired 
	EmployeeRepository employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Employee> employee = Optional.ofNullable(employeeRepo.getEmployeeByLoginName(username));
		
		if (employee.isPresent()) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			String role = "";
			if (employee.get().getEmployeeRole() == 1) role = "Admin";
			else role = "Staff";
			authorities.add(new SimpleGrantedAuthority(role));
			
			return new User(employee.get().getEmployeeLoginName(), employee.get().getEmployeePassword(), authorities);
		}
		else throw new UsernameNotFoundException("User " + username + "does not exists");
	}

}
