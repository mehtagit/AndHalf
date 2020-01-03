package com.gl.CEIR.FileProcess.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.entity.DeviceDb;

public interface DeviceDbRepository extends JpaRepository<DeviceDb, Long> {

	public	List<DeviceDb> getByImporterTxnId(String txnId); 

	public List<DeviceDb> getBySnOfDevice(String serialNumber);

	public Optional<DeviceDb> getByImeiEsnMeid(String imeiEsnMeid);

	public List<DeviceDb> getByEndUserUserId(String endUserId);

}
