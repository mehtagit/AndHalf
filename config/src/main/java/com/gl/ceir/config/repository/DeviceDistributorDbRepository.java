package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.DeviceDistributerDb;

@Repository
public interface DeviceDistributorDbRepository extends JpaRepository<DeviceDistributerDb, Long>, 
JpaSpecificationExecutor<DeviceDistributerDb> {

	public DeviceDistributerDb getByImeiEsnMeid(String imei);

}
