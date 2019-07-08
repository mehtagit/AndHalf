package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.repository.DeviceSnapShotRepository;
import com.gl.ceir.config.service.DeviceSnapShotService;

@Service
public class DeviceSnapShotServiceImpl implements DeviceSnapShotService {

	private static final Logger logger = LogManager.getLogger(DeviceSnapShotServiceImpl.class);

	@Autowired
	private DeviceSnapShotRepository deviceSnapShotRepository;

	@Override
	public List<DeviceSnapShot> getAll() {
		try {
			return deviceSnapShotRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Override

	@Caching(put = { @CachePut(value = "deviceSnapShots", key = "#deviceSnapShot.imei") })
	public DeviceSnapShot save(DeviceSnapShot deviceSnapShot) {
		try {
			return deviceSnapShotRepository.save(deviceSnapShot);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Override
	@Cacheable(value = "deviceSnapShots", key = "#imei")
	public DeviceSnapShot get(Long imei) {
		logger.info("Get Device Snap Shot by Imei:" + imei);
		Optional<DeviceSnapShot> deviceSnapShot = deviceSnapShotRepository.findById(imei);
		if (deviceSnapShot.isPresent())
			return deviceSnapShot.get();
		else
			throw new ResourceNotFoundException("DeviceSnapShot ", "imei", imei);
	}

	@Override
	public DeviceSnapShot update(DeviceSnapShot deviceSnapShot) {
		try {
			return deviceSnapShotRepository.save(deviceSnapShot);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Override
	public void delete(Long t) {
	}

	@Override
	public List<DeviceSnapShot> getByMsisdn(Long msisdn) {
		try {
			List<DeviceSnapShot> deviceSnapShot = deviceSnapShotRepository.findByMsisdn(msisdn);
			return deviceSnapShot;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public List<DeviceSnapShot> saveAll(List<DeviceSnapShot> deviceSnapShots) {
		return deviceSnapShotRepository.saveAll(deviceSnapShots);
	}

	@Override
	public DeviceSnapShot getByImeiAndMsisdn(Long imei, Long msisdn) {
		return deviceSnapShotRepository.findByImeiAndMsisdn(imei, msisdn);
	}

}
