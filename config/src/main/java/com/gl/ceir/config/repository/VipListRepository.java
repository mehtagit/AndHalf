package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.VipList;

public interface VipListRepository extends JpaRepository<VipList, ImeiMsisdnIdentity> {
	public VipList findByImeiMsisdnIdentityMsisdn(Long msisdn);

	public VipList findByImeiMsisdnIdentityImei(String imei);

}
