package com.gl.ceir.config.service;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.WhiteList;

public interface WhiteListService extends RestServices<WhiteList> {
	public WhiteList getByMsisdn(Long msisdn);

	public WhiteList getByImei(Long imei);

	public WhiteList getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void deleteByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);
}
