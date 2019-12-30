package com.gl.CEIR.FileProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.entity.DeviceDbHistory;

public interface StockDetailsOperationRepository extends JpaRepository<DeviceDbHistory, Long>{

	@SuppressWarnings("unchecked")
	DeviceDbHistory save(DeviceDbHistory stockDetailsOperation); 

}
