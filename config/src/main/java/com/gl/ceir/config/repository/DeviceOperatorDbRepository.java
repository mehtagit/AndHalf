package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.factory.CustomerCareRepo;
import com.gl.ceir.config.model.DeviceLawfulDb;
import com.gl.ceir.config.model.DeviceOperatorDb;

@Repository
public interface DeviceOperatorDbRepository extends JpaRepository<DeviceLawfulDb, Long>, 
JpaSpecificationExecutor<DeviceLawfulDb>,
CustomerCareRepo<DeviceOperatorDb>{

	public DeviceOperatorDb getByImeiEsnMeid(String imei);
	
	public DeviceOperatorDb getByImeiEsnMeidAndDeviceState(String imei, Integer deviceState);

}
