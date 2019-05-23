package com.gl.ceir.config.service;

import com.gl.ceir.config.model.BlackList;

public interface BlackListService extends RestServices<BlackList> {
	public BlackList getByMsisdn(Long msisdn);

	public BlackList getByImei(Long imei);

	public BlackList getByMsisdnAndImei(Long msisdn, Long imei);
}
