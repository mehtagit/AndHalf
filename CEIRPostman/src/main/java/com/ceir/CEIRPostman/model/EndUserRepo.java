package com.ceir.CEIRPostman.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EndUserRepo extends JpaRepository<EndUserDB, Long>{

	public EndUserDB findById(long id);
	
}
