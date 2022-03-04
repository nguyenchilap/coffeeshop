package com.cafeteria.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.Beverage;
import com.cafeteria.api.entity.Category;
import com.cafeteria.api.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private CategoryRepository categoryRepo;
	private BeverageService beverageService;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepo, BeverageService beverageService) {
		this.categoryRepo = categoryRepo;
		this.beverageService = beverageService;
	}
	
	//-------- Basic Restful ---------
	
	public List<Category> getAllCategories(){
		return categoryRepo.findAll();
	}
	
	public Category getCategoryById(Integer id) {
		return categoryRepo.findById(id).orElse(null);
	}
	
	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public int deleteCategoryById(Integer id) {
		Category cat = categoryRepo.findById(id).orElse(null);
		if (cat != null) {
			cat.getBeverageList().forEach(bev -> {
				beverageService.deleteBeverageById(bev.getBeverageId());
			});
			categoryRepo.deleteById(id);
			return 1;
		}else return 0;
	}
	
	public Category updateCategory(Category cat) {
		Category existingCat = categoryRepo.findById(cat.getCategoryId()).orElse(null);
		existingCat.setCategoryName(cat.getCategoryName());
		existingCat.setStoreId(cat.getStoreId());
		existingCat.setStore(cat.getStore());
		existingCat.setBeverageList(cat.getBeverageList());
		return categoryRepo.save(existingCat);
	}
	
	public List<Beverage> getBeverageByCategoryId(Integer id){
		return categoryRepo.findById(id).orElse(null).getBeverageList();
	}
	
	public List<Category> getCategoriesByStoreId(Integer id) {
		return categoryRepo.getCategoriesByStoreId(id);
	}
	
}