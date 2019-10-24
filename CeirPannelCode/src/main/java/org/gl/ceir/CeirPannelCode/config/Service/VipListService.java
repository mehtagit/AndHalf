package org.gl.ceir.CeirPannelCode.config.Service;

import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.VipList;

public interface VipListService extends RestServices<VipList> {
	public VipList getByMsisdn(Long msisdn);

	public VipList getByImei(Long imei);

	public VipList getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void deleteByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);
}
