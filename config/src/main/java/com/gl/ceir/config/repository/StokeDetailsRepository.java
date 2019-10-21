package com.gl.ceir.config.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.StokeDetails;

public interface StokeDetailsRepository extends JpaRepository<StokeDetails, Long> {


	public	List<StokeDetails>  findByTxnIdAndSourceType(String txnId,String sourceType); 

	public void deleteByTxnId(String txnId);


	@Transactional
	@Modifying
	@Query(value = "UPDATE consignment  set imei_action= ?1  where txn_id= ?2",
	nativeQuery = true)
	void withdrawStatus(String txnStatus,String txnId);



}
