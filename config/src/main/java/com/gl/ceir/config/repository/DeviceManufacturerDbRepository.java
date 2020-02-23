package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.DeviceManufacturerDb;

@Repository
public interface DeviceManufacturerDbRepository extends JpaRepository<DeviceManufacturerDb, Long>, 
JpaSpecificationExecutor<DeviceManufacturerDb> {

	public DeviceManufacturerDb getByImeiEsnMeid(String imei);

}
