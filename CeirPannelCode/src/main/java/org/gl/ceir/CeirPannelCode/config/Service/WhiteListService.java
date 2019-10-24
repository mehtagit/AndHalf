package org.gl.ceir.CeirPannelCode.config.Service;

import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.WhiteList;

public interface WhiteListService extends RestServices<WhiteList> {
	public WhiteList getByMsisdn(Long msisdn);

	public WhiteList getByImei(Long imei);

	public WhiteList getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void deleteByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);
}
