package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.DeviceEndUserDb;

@Repository
public interface DeviceEndUserDbRepository extends JpaRepository<DeviceEndUserDb, Long>, 
JpaSpecificationExecutor<DeviceEndUserDb> {

	public DeviceEndUserDb getByImeiEsnMeid(String imei);

}
