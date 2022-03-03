package com.cafeteria.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafeteria.api.entity.Order;
import com.cafeteria.api.entity.Voucher;
import com.cafeteria.api.repository.VoucherRepository;

@Service
public class VoucherService {
	
	private VoucherRepository voucherRepo;

	@Autowired
	public VoucherService(VoucherRepository voucherRepo) {
		this.voucherRepo = voucherRepo;
	}
	
	//----basic api-----
	public List<Voucher> getAllVouchers(){
		return voucherRepo.findAll();
	}
	
	public Voucher getVoucherById(Integer id) {
		return voucherRepo.findById(id).orElse(null);
	}
	
	public Voucher addVoucher(Voucher voucher) {
		return voucherRepo.save(voucher);
	}
	
	public int deleteVoucherById(Integer id) {
		Voucher voucher = voucherRepo.findById(id).orElse(null);
		if (voucher != null) {
			voucherRepo.deleteById(id);
			return 1;
		}
		else return 0;
	}
	
	public Voucher updateVoucher(Voucher voucher) {
		Voucher existingVoucher = voucherRepo.findById(voucher.getVoucherId()).orElse(null);
		existingVoucher.setVoucherCode(voucher.getVoucherCode());
		existingVoucher.setVoucherPercentage(voucher.getVoucherPercentage());
		existingVoucher.setVoucherLimit(voucher.getVoucherLimit());
		existingVoucher.setVoucherEndDate(voucher.getVoucherEndDate());
		existingVoucher.setVoucherStartDate(voucher.getVoucherStartDate());
		existingVoucher.setVoucherMax(voucher.getVoucherMax());
		existingVoucher.setVoucherMinOrder(voucher.getVoucherMinOrder());
		return voucherRepo.save(existingVoucher);
	}
	
	//----extended api-----
	
	public Voucher decreaseVoucherLimit(Voucher voucher) {
		if (voucher.getVoucherLimit() > 0)
		voucher.setVoucherLimit(voucher.getVoucherLimit() - 1);
		return voucher;
	}
	
	public Voucher getBestVoucher(Order order) {
		List<Voucher> vouchers = voucherRepo.getVouchersByDate(order.getOrderDate());
		Integer resultIndex = 0;
		Float maxPercentage = 0F;
		
		//find best voucher
		for(Voucher voucher: vouchers) {
			if (voucher.getVoucherMinOrder() <= order.getOrderTotal()) {
				if (voucher.getVoucherPercentage() > maxPercentage) {
					maxPercentage = voucher.getVoucherPercentage();
					resultIndex = vouchers.indexOf(voucher);
				}	
			}
		}
		
		return vouchers.get(resultIndex);
	}
}
