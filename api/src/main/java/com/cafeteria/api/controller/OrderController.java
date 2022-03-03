package com.cafeteria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Order;
import com.cafeteria.api.entity.OrderBeverage;
import com.cafeteria.api.service.OrderService;

@RestController
@RequestMapping("api/v1/order")
@org.springframework.web.bind.annotation.CrossOrigin("http://localhost:4200")
public class OrderController {
	
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//-----basic api------
	
	@GetMapping("/all")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("{id}")
	public Order getOrderById(@PathVariable("id") Integer id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping
	public Order createOrderEmpty (@RequestBody Order order) {
		return this.orderService.addOrder(order);
	}
	
	//------ extended api -------
	
	@PostMapping("/add-beverage-to-order")
	public List<OrderBeverage> addBeverageToOrder (@RequestBody List<OrderBeverage> orderBeverages) {
		return this.orderService.addBeverageToOrder(orderBeverages);
	}
	
}
