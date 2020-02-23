package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.DeviceCustomDb;

@Repository
public interface DeviceCustomDbRepository extends JpaRepository<DeviceCustomDb, Long>, 
JpaSpecificationExecutor<DeviceCustomDb> {

	public DeviceCustomDb getByImeiEsnMeid(String imei);

}
