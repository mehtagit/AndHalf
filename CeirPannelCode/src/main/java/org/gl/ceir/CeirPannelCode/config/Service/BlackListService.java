package org.gl.ceir.CeirPannelCode.config.Service;

import org.gl.ceir.CeirPannelCode.config.Model.BlackList;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;

public interface BlackListService extends RestServices<BlackList> {
	public BlackList getByMsisdn(Long msisdn);

	public BlackList getByImei(Long imei);

	public BlackList getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void deleteByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);
}
