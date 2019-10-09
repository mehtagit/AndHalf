package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.GreyList;

public interface GreyListRepository extends JpaRepository<GreyList, Long> {

	public GreyList save (GreyList greyList);

	public void deleteByImei(Long imei);

	
	
	

}
