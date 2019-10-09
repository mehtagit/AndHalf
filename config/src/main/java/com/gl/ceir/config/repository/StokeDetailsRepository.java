package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.StokeDetails;

public interface StokeDetailsRepository extends JpaRepository<StokeDetails, Long> {


	public	List<StokeDetails>  findByTxnIdAndSourceType(String txnId,String sourceType); 

	public void deleteByTxnId(String txnId);




}
