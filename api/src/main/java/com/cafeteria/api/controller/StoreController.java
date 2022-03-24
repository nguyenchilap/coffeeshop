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

import com.cafeteria.api.entity.ResponseObject;
import com.cafeteria.api.entity.Store;
import com.cafeteria.api.service.StoreService;

@RestController
@RequestMapping("api/v1/store")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8000"})
public class StoreController {
	
	private StoreService storeService;
	
	@Autowired
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	//----basic api-----
	@PostMapping
	public ResponseEntity<ResponseObject> addStore(@RequestBody Store store) {
		Store s = storeService.createStore(store);
		if (s != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", s));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create store !", null));
		}
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllStores() {
		List<Store> s = storeService.getAllStore();
		if (s.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", s));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No store found !", null));
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseObject> getStoreById(@PathVariable("id") Integer id) {
		Store s = storeService.getStoreById(id);
		if (s != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", s));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Store with id " + id + " doesn't exist!", null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseObject> deleteStoreById(@PathVariable("id") Integer id) {
		boolean deleteComplete = storeService.deleteById(id);
		if (deleteComplete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteComplete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Store with id " + id + " doesn't exist!", null));
		}
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<ResponseObject> deleteStoreByIds(@RequestParam List<Integer> ids) {
		boolean deleteComplete = storeService.deleteByIds(ids);
		if (deleteComplete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteComplete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Some stores don't exist!", null));
		}
	}
	
	@PutMapping()
	public ResponseEntity<ResponseObject> editStore(@RequestBody Store store) {
		Store s = storeService.updateStore(store);
		if (s != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", s));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot edit this store !", null));
		}
	}
	
}
