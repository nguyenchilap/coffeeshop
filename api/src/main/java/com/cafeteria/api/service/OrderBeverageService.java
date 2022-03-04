package com.cafeteria.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.OrderBeverage;
import com.cafeteria.api.repository.OrderBeverageRepository;

@Service
public class OrderBeverageService {
	
	private OrderBeverageRepository orderBeverageRepo;

	public OrderBeverageService(OrderBeverageRepository orderBeverageRepo) {
		this.orderBeverageRepo = orderBeverageRepo;
	}
	
	public OrderBeverage createOneOrderBeverage(OrderBeverage orderBeverage) {
		return this.orderBeverageRepo.save(orderBeverage);
	}
	
	public List<OrderBeverage> createMultiOrderBeverage(List<OrderBeverage> orderBeverages) {
		return this.orderBeverageRepo.saveAll(orderBeverages);
	}
	
	public int deleteOrderBeverage(OrderBeverage orderBeverage) {
		this.orderBeverageRepo.delete(orderBeverage);
		return 1;
	}
	
	public void deleteOrderBeveragesByOrderId (Integer orderId) {
		this.orderBeverageRepo.deleteAll(this.orderBeverageRepo.getOrderBeveragesByOrderId(orderId));
	}
	
	public void deleteOrderBeveragesByOrderIds (List<Integer> orderIds) {
		this.orderBeverageRepo.deleteAll(this.orderBeverageRepo.getOrderBeveragesByOrderIds(orderIds));
	}
	
}
