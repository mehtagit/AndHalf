package com.ceir.BlackListProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceir.BlackListProcess.model.BlackList;
import com.ceir.BlackListProcess.model.ImeiMsisdnIdentity;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, ImeiMsisdnIdentity> {
	public BlackList findByImeiMsisdnIdentityMsisdn(Long msisdn);

	public BlackList findByImeiMsisdnIdentityImei(Long imei);
	
	public BlackList save(BlackList blackList);
	 
}
