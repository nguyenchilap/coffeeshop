package com.cafeteria.api.controller;

import java.util.List;

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

import com.cafeteria.api.entity.Category;
import com.cafeteria.api.entity.ResponseObject;
import com.cafeteria.api.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8000"})
public class CategoryController {
	
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllCategories(){
		List<Category> cats = categoryService.getAllCategories();
		if (cats.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", cats));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No category found !", null));
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseObject> getCategoryById(@PathVariable("id") Integer id) {
		Category cat = categoryService.getCategoryById(id);
		if (cat != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", cat));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Category with id " + id + " doesn't exist!", null));
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseObject> createCategory(@RequestBody Category category) {
		Category cat = categoryService.addCategory(category);
		if (cat != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", cat));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create category", null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseObject> deleteCategoryById(@PathVariable("id") Integer id) {
		boolean deleteComplete = categoryService.deleteCategoryById(id);
		if (deleteComplete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteComplete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Category with id " + id + " doesn't exist!", deleteComplete));
		}
	}
	
	@PutMapping()
	public ResponseEntity<ResponseObject> editCategory(@RequestBody Category newCat) {
		Category cat = categoryService.updateCategory(newCat);
		if (cat != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", cat));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create category", null));
		}
	}
	
	//--------extended API-------
	
	@GetMapping("/all-by-id")
	public ResponseEntity<ResponseObject> getAllCategoriesByIds(@RequestParam("ids") List<Integer> ids){
		List<Category> cats = categoryService.getAllCategoriesByIds(ids);
		if (cats.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", cats));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No category found !", null));
		}
	}
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<ResponseObject> getCategoriesByStoreId(@PathVariable("storeId") Integer storeId){
		List<Category> cats =  categoryService.getCategoriesByStoreId(storeId);
		if (cats.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", cats));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No category found !", null));
		}
	}
	
	
}
