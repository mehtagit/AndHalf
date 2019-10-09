package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.BlackListTrackDetails;

public interface BlackListTrackDetailsRepository extends JpaRepository<BlackListTrackDetails, Long> {


	public BlackListTrackDetails save(BlackListTrackDetails blackListTrackDetails);


}
