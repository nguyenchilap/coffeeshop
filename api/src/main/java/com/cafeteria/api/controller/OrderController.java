package com.cafeteria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Order;
import com.cafeteria.api.entity.OrderBeverage;
import com.cafeteria.api.entity.ResponseObject;
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
	public ResponseEntity<ResponseObject> getAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		if (orders.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", orders));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No order found !", null));
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseObject> getOrderById(@PathVariable("id") Integer id) {
		Order order = orderService.getOrderById(id);
		if (order != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", order));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Order with id " + id + " doesn't exist!", null));
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseObject> createOrderEmpty (@RequestBody Order od) {
		Order order = orderService.addOrder(od);
		if (order != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", order));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create this order", null));
		}
	}
	
	@DeleteMapping("{orderId}")
	public ResponseEntity<ResponseObject> deleteOneOrder (@PathVariable("orderId") Integer orderId) {
		boolean deleteComplete = this.orderService.deleteOneOrder(orderId);
		if (deleteComplete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteComplete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Order with id " + orderId + " doesn't exist!", null));
		}
	}
	
	//------ extended api -------
	
	@PostMapping("/add-beverage-to-order")
	public ResponseEntity<ResponseObject> addBeverageToOrder (@RequestBody List<OrderBeverage> orderBeverages) {
		List<OrderBeverage> ob = this.orderService.addBeverageToOrder(orderBeverages);
		if (ob.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", ob));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot import these beverage", null));
		}
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<ResponseObject> deleteMultiOrder (@RequestParam List<Integer> ids) {
		boolean deleteComplete = this.orderService.deleteMultiOrder(ids);
		if (deleteComplete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteComplete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Some stores don't exist", null));
		}
	}
	
}
