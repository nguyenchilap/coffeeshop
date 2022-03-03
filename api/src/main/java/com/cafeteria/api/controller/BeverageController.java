package com.cafeteria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Beverage;
import com.cafeteria.api.service.BeverageService;

@RestController
@RequestMapping("api/v1/beverage")
@org.springframework.web.bind.annotation.CrossOrigin("http://localhost:4200")
public class BeverageController {
	
	private BeverageService beverageService;

	@Autowired
	public BeverageController(BeverageService beverageService) {
		this.beverageService = beverageService;
	}
	
	@GetMapping("/all")
	public List<Beverage> getAllBeverage() {
		return beverageService.getAllBeverages();
	}
	
	@GetMapping("{id}")
	public Beverage getBeverageById(@PathVariable Integer id) {
		return beverageService.getBeverageById(id);
	}
	
	@PostMapping()
	public Beverage createBeverage(@RequestBody Beverage bev) {
		return beverageService.addBeverage(bev);
	}
	
	@PostMapping("/all")
	public List<Beverage> createBeverages(@RequestBody List<Beverage> beverages) {
		return beverageService.addBeverages(beverages);
	}
	
	@DeleteMapping("{id}")
	public int deleteBeverageById(@PathVariable Integer id) {
		return beverageService.deleteBeverageById(id);
	}
	
	@PutMapping
	public Beverage editBeverage(@RequestBody Beverage bev) {
		return beverageService.updateBeverage(bev);
	}
	//---------extended API----------
	
	@GetMapping("/category/{categoryId}")
	public List<Beverage> getBeveragesByCategoryId(@PathVariable Integer categoryId) {
		return beverageService.getBeveragesByCategoryId(categoryId);
	}
	
	@GetMapping("/find-by-ids")
	public List<Beverage> getBeveragesByIds(@RequestParam List<Integer> beverageIds) {
		return beverageService.getBeveragesByBeverageIds(beverageIds);
	}
	
}
