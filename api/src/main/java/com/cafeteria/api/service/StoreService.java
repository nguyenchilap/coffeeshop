package com.cafeteria.api.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.Category;
import com.cafeteria.api.entity.Store;
import com.cafeteria.api.repository.StoreRepository;

@Service
public class StoreService {
	
	private StoreRepository storeRepo;
	
	@Autowired
	public StoreService(StoreRepository storeRepo) {
		this.storeRepo = storeRepo;
	}
	
	//----- basic restful -----
	public Store createStore(Store store) {
		return storeRepo.save(store);
	}

	public Store getStoreById(Integer id) {
		return storeRepo.findById(id).orElse(null);
	}
	
	public List<Store> getAllStore() {
		return storeRepo.findAll();
	}
	
	public int deleteById(Integer id) {
		Store store = storeRepo.findById(id).orElse(null);
		if (store == null) return 0;
		else {
			storeRepo.delete(store);
			return 1;
		}		
	}

	public Store updateStore(Store store) {
		Store existingStore = storeRepo.findById(store.getStoreId()).orElse(null);
		existingStore.setStoreName(store.getStoreName());
		existingStore.setStoreAddress(store.getStoreAddress());
		existingStore.setStoreImage(store.getStoreImage());
		return storeRepo.save(existingStore);
	}
	
	//----- extended API ------
	
	public Set<Category> getAllCategoriesOfStore(Integer id){
		return storeRepo.findById(id).orElse(null).getCategoryList();
	}
}
