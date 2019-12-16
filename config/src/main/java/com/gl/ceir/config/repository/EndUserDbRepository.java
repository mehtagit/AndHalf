package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.EndUserDB;

public interface EndUserDbRepository extends JpaRepository<EndUserDB, Long> {

	@SuppressWarnings("unchecked")
	public EndUserDB save (EndUserDB customRegistrationDB);
	
	public EndUserDB getByNid(String nid);
	
}
