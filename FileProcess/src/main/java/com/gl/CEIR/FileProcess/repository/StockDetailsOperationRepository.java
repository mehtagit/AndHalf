package com.gl.CEIR.FileProcess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.DeviceDbHistory;


public interface StockDetailsOperationRepository extends JpaRepository<DeviceDbHistory, Long>{

	
	DeviceDbHistory save(DeviceDbHistory stockDetailsOperation); 
	
	
}
