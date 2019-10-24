package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

import org.gl.ceir.CeirPannelCode.config.Model.DuplicateImeiMsisdn;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.PendingActions;

public interface DuplicateImeiMsisdnService extends RestServices<DuplicateImeiMsisdn> {
	public DuplicateImeiMsisdn get(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public DuplicateImeiMsisdn update(DuplicateImeiMsisdn DeviceSnapShot);

	public List<DuplicateImeiMsisdn> saveAll(List<DuplicateImeiMsisdn> duplicateImeiMsisdns);
}
