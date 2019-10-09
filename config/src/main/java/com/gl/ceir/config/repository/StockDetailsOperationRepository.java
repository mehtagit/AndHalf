package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.StockDetailsOperation;

public interface StockDetailsOperationRepository extends JpaRepository<StockDetailsOperation, Long>{

	
	StockDetailsOperation save(StockDetailsOperation stockDetailsOperation); 
	
	
}
