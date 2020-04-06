package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.factory.CustomerCareRepo;
import com.gl.ceir.config.model.DeviceDuplicateDb;

@Repository
public interface DeviceDuplicateDbRepository extends JpaRepository<DeviceDuplicateDb, Long>, 
JpaSpecificationExecutor<DeviceDuplicateDb>, CustomerCareRepo<DeviceDuplicateDb> {

	public DeviceDuplicateDb findByImeiMsisdnIdentityMsisdn(Long msisdn);

	public DeviceDuplicateDb findByImeiMsisdnIdentityImei(String imei);

}
