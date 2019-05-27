package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.VipList;

public interface BlackListRepository extends JpaRepository<BlackList, ImeiMsisdnIdentity> {
	public BlackList findByImeiMsisdnIdentityMsisdn(Long msisdn);

	public BlackList findByImeiMsisdnIdentityImei(Long imei);
}
