package com.gl.CEIR.FileProcess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.CEIR.FileProcess.model.GreylistDb;


public interface GreyListRepository extends JpaRepository<GreylistDb, Long> {

	public GreylistDb save (GreylistDb greyList);

	public void deleteByImei(Long imei);

	
	
	

}
