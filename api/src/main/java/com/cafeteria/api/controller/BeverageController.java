package com.cafeteria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.cafeteria.api.entity.ResponseObject;
import com.cafeteria.api.service.BeverageService;

@RestController
@RequestMapping("api/v1/beverage")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8000"})
public class BeverageController {
	
	private BeverageService beverageService;

	@Autowired
	public BeverageController(BeverageService beverageService) {
		this.beverageService = beverageService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllBeverage() {
		List<Beverage> bevs = beverageService.getAllBeverages();
		if (bevs.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", bevs));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No beverage found !", null));
		}	
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseObject> getBeverageById(@PathVariable("id") Integer id) {
		Beverage bev = beverageService.getBeverageById(id);
		if (bev != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", bev));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Beverage with id " + id + " doesn't exist!", null));
		}	
	}
	
	@PostMapping()
	public ResponseEntity<ResponseObject> createBeverage(@RequestBody Beverage beverage) {
		Beverage bev = beverageService.addBeverage(beverage);
		if (bev != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", bev));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create this beverage !", null));
		}	
	}
	
	@PostMapping("/all")
	public ResponseEntity<ResponseObject> createBeverages(@RequestBody List<Beverage> beverages) {
		List<Beverage> bevs = beverageService.addBeverages(beverages);
		if (bevs.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", bevs));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create these beverage !", null));
		}	
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseObject> deleteBeverageById(@PathVariable("id") Integer id) {
		boolean deleteComplete = beverageService.deleteBeverageById(id);
		if (deleteComplete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteComplete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Beverage with id " + id + " doesn't exist!", null));
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseObject> editBeverage(@RequestBody Beverage newBev) {
		Beverage bev = beverageService.updateBeverage(newBev);
		if (bev != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", bev));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot edit this beverage !", null));
		}		 
	}
	
	//---------extended API----------
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<ResponseObject> getBeveragesByCategoryId(@PathVariable("categoryId") Integer categoryId) {
		List<Beverage> bevs = beverageService.getBeveragesByCategoryId(categoryId);
		if (bevs.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", bevs));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No beverage found with category" + categoryId + "!", null));
		}	
	}
	
	@GetMapping("/find-by-ids")
	public ResponseEntity<ResponseObject> getBeveragesByIds(@RequestParam List<Integer> beverageIds) {
		List<Beverage> bevs = beverageService.getBeveragesByBeverageIds(beverageIds);
		if (bevs.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", bevs));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No beverage found !", null));
		}	
	}
	
}
