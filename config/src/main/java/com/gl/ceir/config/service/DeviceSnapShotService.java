package com.gl.ceir.config.service;

import java.util.List;

import com.gl.ceir.config.model.DeviceSnapShot;

public interface DeviceSnapShotService extends RestServices<DeviceSnapShot> {

	public List<DeviceSnapShot> getByMsisdn(Long msisdn);

	public DeviceSnapShot getByImeiAndMsisdn(Long imei, Long msisdn);

	public void delete(Long imei);

	public DeviceSnapShot update(DeviceSnapShot deviceSnapShot);

	public List<DeviceSnapShot> saveAll(List<DeviceSnapShot> deviceSnapShots);
}
