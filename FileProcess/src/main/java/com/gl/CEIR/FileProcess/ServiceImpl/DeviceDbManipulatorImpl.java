package com.gl.CEIR.FileProcess.ServiceImpl;

import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.service.DeviceDbManipulator;

@Component
public final class DeviceDbManipulatorImpl implements DeviceDbManipulator {

	@Override
	public void setDefault(DeviceDb deviceDb) {

		deviceDb.setDistributerDeviceStatus(-1);
		deviceDb.setImporterDeviceStatus(-1);
		deviceDb.setPreviousDistributerDeviceStatus(-1);
		deviceDb.setPreviousImporterDeviceStatus(-1);
		deviceDb.setPreviousRetailerDeviceStatus(-1);
		deviceDb.setRetailerDeviceStatus(-1);
		deviceDb.setCustomDeviceStatus(-1);
		deviceDb.setEndUserDeviceStatus(-1);
		deviceDb.setPreviousCustomDeviceStatus(-1);
		deviceDb.setPreviousEndUserDeviceStatus(-1);
	
	}

}
