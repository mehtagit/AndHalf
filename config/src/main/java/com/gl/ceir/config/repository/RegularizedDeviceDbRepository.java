package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.RegularizeDeviceDb;

public interface RegularizedDeviceDbRepository extends JpaRepository<RegularizeDeviceDb, Long>, JpaSpecificationExecutor<RegularizeDeviceDb	> {

	public RegularizeDeviceDb getByDeviceSerialNumber(String serialNumber);

	public void deleteByDeviceSerialNumber(String serialNumber);

	public List<RegularizeDeviceDb> getByNid(String nid);
	
	public RegularizeDeviceDb getByFirstImei(Long imei1);
	
	public Long countByNid(String nid);
	
	public RegularizeDeviceDb getByTxnId(String txnid);

}
