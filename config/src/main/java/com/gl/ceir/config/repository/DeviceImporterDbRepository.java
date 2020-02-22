package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.DeviceImporterDb;

@Repository
public interface DeviceImporterDbRepository extends JpaRepository<DeviceImporterDb, Long>, 
JpaSpecificationExecutor<DeviceImporterDb> {

	public DeviceImporterDb getByImeiEsnMeid(String imei);

}
