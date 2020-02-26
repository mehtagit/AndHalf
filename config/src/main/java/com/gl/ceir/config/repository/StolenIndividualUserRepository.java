package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.StolenIndividualUserDB;

@Repository
public interface StolenIndividualUserRepository extends JpaRepository<StolenIndividualUserDB, Long>,
JpaSpecificationExecutor<StolenIndividualUserDB> {
	
}
