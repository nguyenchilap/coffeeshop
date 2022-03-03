package com.cafeteria.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafeteria.api.entity.Beverage;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Integer> {
	
	@Autowired
	@Query("SELECT b FROM beverage b WHERE CategoryId = :categoryId")
	public List<Beverage> findBeveragesByCategoryId(@Param("categoryId") Integer categoryId);
	
	@Autowired
	@Query("SELECT b FROM beverage b WHERE BeverageId in :beverageIds")
	public List<Beverage> findBeveragesByIds(@Param("beverageIds") List<Integer> beverageIds);
}
