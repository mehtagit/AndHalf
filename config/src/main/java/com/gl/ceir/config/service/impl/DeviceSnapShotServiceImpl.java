package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.repository.DeviceSnapShotRepository;
import com.gl.ceir.config.service.DeviceSnapShotService;

@Service
public class DeviceSnapShotServiceImpl implements DeviceSnapShotService {

	@Autowired
	private DeviceSnapShotRepository deviceSnapShotRepository;

	@Override
	public List<DeviceSnapShot> getAll() {
		return deviceSnapShotRepository.findAll();
	}

	@Override
	public DeviceSnapShot save(DeviceSnapShot deviceSnapShot) {
		return deviceSnapShotRepository.save(deviceSnapShot);
	}

	@Override
	public DeviceSnapShot get(Long id) {
		return null;
	}

	@Override
	public DeviceSnapShot update(DeviceSnapShot deviceSnapShot) {
		return deviceSnapShotRepository.save(deviceSnapShot);
	}

	@Override
	public void delete(Long t) {
	}

	@Override
	public DeviceSnapShot get(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		DeviceSnapShot deviceSnapShot = deviceSnapShotRepository.findById(imeiMsisdnIdentity).orElseThrow(
				() -> new ResourceNotFoundException("Device SnapShot", "imeiMsisdnIdentity", imeiMsisdnIdentity));
		return deviceSnapShot;
	}

	@Override
	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		deviceSnapShotRepository.deleteById(imeiMsisdnIdentity);
	}

}
