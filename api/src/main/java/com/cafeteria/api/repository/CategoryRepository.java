package com.cafeteria.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafeteria.api.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Autowired
	@Query("SELECT c FROM category c WHERE StoreId = :storeId")
	public List<Category> getCategoriesByStoreId(@Param("storeId") Integer storeId);

}
