package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.DeviceDb;


public interface StokeDetailsRepository extends JpaRepository<DeviceDb, Long> {

	public List<DeviceDb> getBySnOfDevice(String serialNumber);
	public DeviceDb getByImeiEsnMeid(String device);

}
