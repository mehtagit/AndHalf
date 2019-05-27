package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.WhiteList;

public interface WhiteListRepository extends JpaRepository<WhiteList, ImeiMsisdnIdentity> {
	public WhiteList findByImeiMsisdnIdentityMsisdn(Long msisdn);

	public WhiteList findByImeiMsisdnIdentityImei(Long imei);
}
