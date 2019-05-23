package com.gl.ceir.config.service;

import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DuplicateImeiMsisdnService extends RestServices<DuplicateImeiMsisdn> {
	public DuplicateImeiMsisdn get(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public DuplicateImeiMsisdn update(DuplicateImeiMsisdn DeviceSnapShot);
}
