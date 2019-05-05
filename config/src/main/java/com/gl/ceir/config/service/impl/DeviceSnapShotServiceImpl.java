package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.repository.DeviceSnapShotRepository;
import com.gl.ceir.config.service.DeviceSnapShotService;

@Service
public class DeviceSnapShotServiceImpl implements DeviceSnapShotService {

	@Autowired
	private DeviceSnapShotRepository deviceSnapShotRepository;

	@Override
	public List<DeviceSnapShot> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceSnapShot save(DeviceSnapShot t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceSnapShot get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceSnapShot update(DeviceSnapShot t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
