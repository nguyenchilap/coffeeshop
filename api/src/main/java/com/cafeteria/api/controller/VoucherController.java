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
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.api.entity.Order;
import com.cafeteria.api.entity.ResponseObject;
import com.cafeteria.api.entity.Voucher;
import com.cafeteria.api.service.VoucherService;

@RestController
@RequestMapping("/api/v1/voucher")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8000"})
public class VoucherController {
	
	private VoucherService voucherService;

	@Autowired
	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllVoucher(){
		List<Voucher> vouchers = voucherService.getAllVouchers();
		if (vouchers.size() > 0) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", vouchers));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No voucher found !", null));
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ResponseObject>  getVoucherById(@PathVariable("id") Integer id) {
		Voucher voucher = voucherService.getVoucherById(id);
		if (voucher != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", voucher));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "No voucher found !", null));
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseObject>  createVoucher(@RequestBody Voucher v) {
		Voucher voucher = voucherService.addVoucher(v);
		if (voucher != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", voucher));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot create this voucher !", null));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseObject>  deleteVoucherById(@PathVariable("id") Integer id) {
		boolean deleteCompete = voucherService.deleteVoucherById(id);
		if (deleteCompete) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", deleteCompete));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed",  "Voucher with id " + id + " doesn't exist!", null));
		}
	}
	
	@PutMapping
	public ResponseEntity<ResponseObject>  updateVoucher(@RequestBody Voucher v) {
		Voucher voucher = voucherService.updateVoucher(v);
		if (voucher != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", voucher));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Cannot edit this voucher !", null));
		}
	}
	
	//--extend api-----------
	//-----------------------
	
	@PostMapping("/get-best-voucher") 
	public ResponseEntity<ResponseObject> getBestVoucherForOrder(@RequestBody Order order) {
		Voucher voucher = voucherService.getBestVoucher(order);
		if (voucher != null) {
			return ResponseEntity.ok(new ResponseObject("complete", "Successfully !", voucher));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "No voucher found for this order !", null));
		}
	}
	
}
