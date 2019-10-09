package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.BlackListImeiDetails;

public interface BlackListImeiDetailsRepository extends JpaRepository<BlackListImeiDetails,Long> {




	public 	BlackListImeiDetails save(BlackListImeiDetails blackListImeiDetails);

	public BlackListImeiDetails getByImei(Long imei);


	public void deleteByImei(Long imei);


}
