package com.gl.CEIR.FileProcess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.entity.DeviceDb;

public interface StokeDetailsRepository extends JpaRepository<DeviceDb, Long> {

	public	List<DeviceDb>  getByImporterTxnId(String txnId); 

	//public void deleteByTxnId(String txnId);

	public List<DeviceDb> getBySnOfDevice(String serialNumber);

	public DeviceDb getByImeiEsnMeid(String device);

	public List<DeviceDb> getByEndUserUserId(String endUserId);

}
