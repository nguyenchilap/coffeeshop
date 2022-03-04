package com.cafeteria.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafeteria.api.entity.OrderBeverage;

@Repository
public interface OrderBeverageRepository extends JpaRepository<OrderBeverage, Integer> {
	
	@Autowired
	@Query("SELECT ob FROM order_beverage ob WHERE OrderId = :orderId")
	public List<OrderBeverage> getOrderBeveragesByOrderId(@Param("orderId") Integer orderId);
	
	@Autowired
	@Query("SELECT ob FROM order_beverage ob WHERE OrderId in :orderIds")
	public List<OrderBeverage> getOrderBeveragesByOrderIds(@Param("orderIds") List<Integer> orderIds);
}
