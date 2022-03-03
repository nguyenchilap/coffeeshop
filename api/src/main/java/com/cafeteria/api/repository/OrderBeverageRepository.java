package com.cafeteria.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.api.entity.OrderBeverage;

public interface OrderBeverageRepository extends JpaRepository<OrderBeverage, Integer> {

}
