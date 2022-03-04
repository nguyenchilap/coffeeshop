package com.cafeteria.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.Beverage;
import com.cafeteria.api.repository.BeverageRepository;

@Service
public class BeverageService {
	
	private BeverageRepository beverageRepo;

	@Autowired
	public BeverageService(BeverageRepository beverageRepo) {
		this.beverageRepo = beverageRepo;
	}
	
	public List<Beverage> getAllBeverages(){
		return beverageRepo.findAll();
	}
	
	public Beverage getBeverageById(Integer id) {
		return beverageRepo.findById(id).orElse(null);
	}
	
	public Beverage addBeverage(Beverage bev) {
		return beverageRepo.save(bev);
	}
	
	public List<Beverage> addBeverages(List<Beverage> bevs) {
		return beverageRepo.saveAll(bevs);
	}
	
	public boolean deleteBeverageById(Integer id) {
		Beverage beverage = beverageRepo.findById(id).orElse(null);
		if (beverage != null) {
			beverageRepo.delete(beverage);
			return true;
		}
		else return false;
	}
	
	public Beverage updateBeverage(Beverage beverage) {
		Beverage existingBev = beverageRepo.findById(beverage.getBeverageId()).orElse(null);
		existingBev.setBeveragePrice(beverage.getBeveragePrice());
		existingBev.setBeverageSize(beverage.getBeverageSize());
		existingBev.setCategoryId(beverage.getCategoryId());
		return beverageRepo.save(existingBev);
	}
	
	public List<Beverage> getBeveragesByCategoryId (Integer id){
		return beverageRepo.findBeveragesByCategoryId(id);
	}
	
	public List<Beverage> getBeveragesByBeverageIds (List<Integer> ids){
		return beverageRepo.findBeveragesByIds(ids);
	}
}
