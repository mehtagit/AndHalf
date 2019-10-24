package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.VipList;

public interface VipListRepository extends JpaRepository<VipList, ImeiMsisdnIdentity> {
	public VipList findByImeiMsisdnIdentityMsisdn(Long msisdn);

	public VipList findByImeiMsisdnIdentityImei(Long imei);

}
