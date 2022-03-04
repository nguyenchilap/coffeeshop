package com.cafeteria.api.controller;

import java.util.List;

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
import com.cafeteria.api.service.CategoryService;

@RestController
@RequestMapping("api/v1/category")
@org.springframework.web.bind.annotation.CrossOrigin("http://localhost:4200")
public class CategoryController {
	
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/all")
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/all-by-id")
	public List<Category> getAllCategoriesByIds(@RequestParam("ids") List<Integer> ids){
		return categoryService.getAllCategoriesByIds(ids);
	}
	
	@GetMapping("{id}")
	public Category getCategoryById(@PathVariable("id") Integer id) {
		return categoryService.getCategoryById(id);
	}
	
	@PostMapping
	public Category createCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@DeleteMapping("{id}")
	public int deleteCategoryById(@PathVariable("id") Integer id) {
		return categoryService.deleteCategoryById(id);
	}
	
	@PutMapping()
	public Category editCategory(@RequestBody Category cat) {
		return categoryService.updateCategory(cat);
	}
	
	//--------extended API-------
	@GetMapping("/store/{storeId}")
	public List<Category> getCategoriesByStoreId(@PathVariable("storeId") Integer storeId){
		return categoryService.getCategoriesByStoreId(storeId);
	}
	
	
}
