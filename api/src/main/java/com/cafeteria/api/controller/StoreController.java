package com.cafeteria.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Category;
import com.cafeteria.api.entity.Store;
import com.cafeteria.api.service.StoreService;

@RestController
@RequestMapping("api/v1/store")
@org.springframework.web.bind.annotation.CrossOrigin("http://localhost:4200")
public class StoreController {
	
	private StoreService storeService;
	
	@Autowired
	public StoreController(StoreService storeService) {
		super();
		this.storeService = storeService;
	}
	
	@PostMapping
	public Store addStore(@RequestBody Store store) {
		return storeService.createStore(store);
	}

	@GetMapping("/all")
	public List<Store> getAllStores() {
		return storeService.getAllStore();
	}
	
	@GetMapping("{id}")
	public Store getStoreById(@PathVariable("id") Integer id) {
		return storeService.getStoreById(id);
	}
	
	@DeleteMapping("{id}")
	public int deleteStoreById(@PathVariable("id") Integer id) {
		return storeService.deleteById(id);
	}
	
	@PutMapping()
	public Store editStore(@RequestBody Store store) {
		return storeService.updateStore(store);
	}
	
	//----- extended api -----
	@GetMapping("{id}/categories")
	public Set<Category> getAllCategory(@PathVariable("id") Integer id){
		return storeService.getAllCategoriesOfStore(id);
	}
	
}
