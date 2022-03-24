package com.cafeteria.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.Order;
import com.cafeteria.api.entity.OrderBeverage;
import com.cafeteria.api.entity.Voucher;
import com.cafeteria.api.repository.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepo;
	private OrderBeverageService orderBeverageService;
	private VoucherService voucherService;
	
	@Autowired
	public OrderService(OrderRepository orderRepo, OrderBeverageService orderBeverageService, VoucherService voucherService) {
		this.orderRepo = orderRepo;
		this.orderBeverageService = orderBeverageService;
		this.voucherService = voucherService;
	}
		
	public List<Order> getAllOrders(){
		return orderRepo.findAll();
	}
	
	public Order getOrderById(Integer id) {
		return orderRepo.findById(id).orElse(null);
	}
	
	public Order addOrder(Order order) {
		if (order.getVoucherId() != null) {
			Voucher voucher = voucherService.getVoucherById(order.getVoucherId());
			if (voucher != null)
				voucherService.decreaseVoucherLimit(voucher);
		}
		return orderRepo.save(order);
	}
	
	public List<OrderBeverage> addBeverageToOrder(List<OrderBeverage> orderBeverages) {
		return this.orderBeverageService.createMultiOrderBeverage(orderBeverages);
	}
	
	public boolean deleteOneOrder(Integer orderId) {
		Order order = orderRepo.findById(orderId).orElse(null);
		if (order != null) {
			this.orderBeverageService.deleteOrderBeveragesByOrderId(orderId);
			this.orderRepo.deleteById(orderId);
			return true;
		} else return false;
	}
	
	public boolean deleteMultiOrder(List<Integer> orderIds) {
		List<Order> orders = orderRepo.findAllById(orderIds);
		if (orders.size() == orderIds.size()) {
			this.orderBeverageService.deleteOrderBeveragesByOrderIds(orderIds);
			this.orderRepo.deleteAllById(orderIds);
			return true;
		} return false;
	}
}
