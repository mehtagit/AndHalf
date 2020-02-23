package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.DeviceRetailerDb;

@Repository
public interface DeviceRetailerDbRepository extends JpaRepository<DeviceRetailerDb, Long>, 
JpaSpecificationExecutor<DeviceRetailerDb> {

	public DeviceRetailerDb getByImeiEsnMeid(String imei);

}
