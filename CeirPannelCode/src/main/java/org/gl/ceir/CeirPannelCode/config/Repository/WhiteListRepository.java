package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.VipList;
import org.gl.ceir.CeirPannelCode.config.Model.WhiteList;

public interface WhiteListRepository extends JpaRepository<WhiteList, ImeiMsisdnIdentity> {
	public WhiteList findByImeiMsisdnIdentityMsisdn(Long msisdn);

	public WhiteList findByImeiMsisdnIdentityImei(Long imei);
}
