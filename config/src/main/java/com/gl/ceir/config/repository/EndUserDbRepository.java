package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.CustomRegistrationDB;

public interface CustomRegisterationDbRepository extends JpaRepository<CustomRegistrationDB, Long> {

	public CustomRegistrationDB save (CustomRegistrationDB customRegistrationDB);
	
}
