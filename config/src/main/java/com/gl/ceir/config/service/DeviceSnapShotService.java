package com.gl.ceir.config.service;

import java.util.List;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;

public interface DeviceSnapShotService extends RestServices<DeviceSnapShot> {

	public List<DeviceSnapShot> get(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public List<DeviceSnapShot> getByMsisdn(Long msisdn);

	public List<DeviceSnapShot> getByImei(Long imei);

	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public DeviceSnapShot update(DeviceSnapShot DeviceSnapShot);

}
