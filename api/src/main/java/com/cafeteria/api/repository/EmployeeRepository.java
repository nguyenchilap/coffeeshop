package com.cafeteria.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafeteria.api.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Autowired
	@Query("SELECT e FROM employee e WHERE EmployeeLoginName = :loginName")
	public Employee getEmployeeByLoginName(@Param("loginName") String loginName);
	
	@Autowired
	@Query("SELECT e FROM employee e WHERE StoreId = :storeId")
	public List<Employee> getEmployeesByStoreId(@Param("storeId") Integer storeId);
}
