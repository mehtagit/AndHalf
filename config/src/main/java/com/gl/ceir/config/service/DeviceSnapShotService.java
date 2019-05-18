package com.gl.ceir.config.service;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DeviceSnapShotService extends RestServices<DeviceSnapShot> {

	public DeviceSnapShot get(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public DeviceSnapShot update(DeviceSnapShot DeviceSnapShot);

}
