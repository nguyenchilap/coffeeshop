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
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Order;
import com.cafeteria.api.entity.Voucher;
import com.cafeteria.api.service.VoucherService;

@RestController
@RequestMapping("/api/v1/voucher")
@org.springframework.web.bind.annotation.CrossOrigin("http://localhost:4200")
public class VoucherController {
	
	private VoucherService voucherService;

	@Autowired
	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}
	
	@GetMapping("/all")
	public List<Voucher> getAllVoucher(){
		return voucherService.getAllVouchers();
	}
	
	@GetMapping("{id}")
	public Voucher getVoucherById(@PathVariable("id") Integer id) {
		return voucherService.getVoucherById(id);
	}
	
	@PostMapping
	public Voucher createVoucher(@RequestBody Voucher voucher) {
		return voucherService.addVoucher(voucher);
	}
	
	@DeleteMapping("{id}")
	public int deleteVoucherById(@PathVariable("id") Integer id) {
		return voucherService.deleteVoucherById(id);
	}
	
	@PutMapping
	public Voucher updateVoucher(@RequestBody Voucher voucher) {
		return voucherService.updateVoucher(voucher);
	}
	
	//--extend api-----------
	//-----------------------
	
	@PostMapping("/get-best-voucher") 
	public Voucher getBestVoucherForOrder(@RequestBody Order order) {
		return voucherService.getBestVoucher(order);
	}
	
}
