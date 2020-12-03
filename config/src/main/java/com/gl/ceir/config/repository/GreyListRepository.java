package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.factory.CustomerCareRepo;
import com.gl.ceir.config.model.GreylistDb;

public interface GreyListRepository extends JpaRepository<GreylistDb, Long>, 
JpaSpecificationExecutor<GreylistDb>{
	
	@Query("SELECT r FROM GreylistDb r WHERE imei = :imei OR substr(imei,1,14) =:imei")
	public GreylistDb findByImei(String imei);
	public void deleteByImei(String imei);
 
}
