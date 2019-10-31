package com.gl.ceir.config.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.DeviceDb;

public interface StokeDetailsRepository extends JpaRepository<DeviceDb, Long> {


	public	List<DeviceDb>  getByImporterTxnId(String txnId); 

	public void deleteByTxnId(String txnId);



	public DeviceDb getByImeiEsnMeid(String device);



	@Transactional
	@Modifying
	@Query(value = "UPDATE consignment  set imei_action= ?1  where txn_id= ?2",
	nativeQuery = true)
	void withdrawStatus(String txnStatus,String txnId);



}
