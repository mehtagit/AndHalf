package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;

import com.gl.ceir.config.model.EndUserDB;

public interface EndUserDbRepository extends RevisionRepository<EndUserDB, Long, Long>, 
JpaRepository<EndUserDB, Long>, JpaSpecificationExecutor<EndUserDB> {

	@SuppressWarnings("unchecked")
	public EndUserDB save (EndUserDB customRegistrationDB);
	
	public EndUserDB getByTxnId(String txnId);

	public EndUserDB getByNid(String nid);
	
}
