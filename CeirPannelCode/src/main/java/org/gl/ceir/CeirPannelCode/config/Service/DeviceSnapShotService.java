package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

import org.gl.ceir.CeirPannelCode.config.Model.DeviceSnapShot;

public interface DeviceSnapShotService extends RestServices<DeviceSnapShot> {

	public void delete(Long imei);

	public DeviceSnapShot update(DeviceSnapShot deviceSnapShot);

	public List<DeviceSnapShot> saveAll(List<DeviceSnapShot> deviceSnapShots);
}
