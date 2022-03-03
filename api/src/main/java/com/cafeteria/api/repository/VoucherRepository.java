package com.cafeteria.api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafeteria.api.entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer>{
	
	@Autowired
	@Query("SELECT v FROM voucher v WHERE VoucherStartDate <= :date and VoucherEndDate >= :date and VoucherLimit > 0")
	public List<Voucher> getVouchersByDate(@Param("date") Date date);
	
}
