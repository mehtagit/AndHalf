package com.gl.CEIR.FileProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.fileprocess.model.GreylistDb;


public interface GreyListRepository extends JpaRepository<GreylistDb, Long> {

	public GreylistDb save (GreylistDb greyList);

	public void deleteByImei(Long imei);

	
	
	

}
