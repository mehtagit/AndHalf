package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.VisaHistoryDb;

public interface VisaHistoryDBRepository extends JpaRepository<VisaHistoryDb, Long> {
	
}
