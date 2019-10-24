package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.StockDetailsOperation;

public interface StockDetailsOperationRepository extends JpaRepository<StockDetailsOperation, Long>{

	
	StockDetailsOperation save(StockDetailsOperation stockDetailsOperation); 
	
	
}
