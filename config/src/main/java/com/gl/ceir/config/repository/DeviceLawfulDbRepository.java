package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.factory.CustomerCareRepo;
import com.gl.ceir.config.model.DeviceLawfulDb;

@Repository
public interface DeviceLawfulDbRepository extends JpaRepository<DeviceLawfulDb, Long>, 
JpaSpecificationExecutor<DeviceLawfulDb>,
CustomerCareRepo<DeviceLawfulDb>{

	public DeviceLawfulDb getByImeiEsnMeid(String imei);
	
	public DeviceLawfulDb getByImeiEsnMeidAndDeviceState(String imei, Integer deviceState);

}
