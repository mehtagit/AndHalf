package com.gl.ceir.config.service;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.VipList;

public interface VipListService extends RestServices<VipList> {
	public VipList getByMsisdn(Long msisdn);

	public VipList getByImei(Long imei);

	public VipList getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void deleteByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);
}
