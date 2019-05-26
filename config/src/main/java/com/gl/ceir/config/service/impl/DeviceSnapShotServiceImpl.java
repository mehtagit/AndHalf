package com.gl.ceir.config.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
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
	public DeviceSnapShot save(DeviceSnapShot deviceSnapShot) {
		try {
			return deviceSnapShotRepository.save(deviceSnapShot);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Override
	public DeviceSnapShot get(Long id) {
		return null;
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
	public List<DeviceSnapShot> get(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		logger.info("Going to get Black List Devices by " + imeiMsisdnIdentity);
		try {
			if (imeiMsisdnIdentity.getMsisdn() == null && imeiMsisdnIdentity.getImei() == null) {
				throw new ResourceNotFoundException("Device SnapShot", "imeiMsisdnIdentity cannot be null",
						imeiMsisdnIdentity);
			} else if (imeiMsisdnIdentity.getMsisdn() != null && imeiMsisdnIdentity.getImei() != null) {
				DeviceSnapShot deviceSnapShot = deviceSnapShotRepository.findById(imeiMsisdnIdentity)
						.orElseThrow(() -> new ResourceNotFoundException("Device SnapShot", "imeiMsisdnIdentity",
								imeiMsisdnIdentity));
				List<DeviceSnapShot> list = new ArrayList<>();
				list.add(deviceSnapShot);
				return list;
			} else if (imeiMsisdnIdentity.getMsisdn() != null) {
				return getByMsisdn(imeiMsisdnIdentity.getMsisdn());
			} else {
				return getByImei(imeiMsisdnIdentity.getImei());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Override
	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		try {
			deviceSnapShotRepository.deleteById(imeiMsisdnIdentity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Override
	public List<DeviceSnapShot> getByMsisdn(Long msisdn) {
		try {
			List<DeviceSnapShot> deviceSnapShot = deviceSnapShotRepository.findByImeiMsisdnIdentityMsisdn(msisdn);
			return deviceSnapShot;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public List<DeviceSnapShot> getByImei(Long imei) {
		try {
			logger.info("Get Device Snap Shot by Imei:"+imei);
			List<DeviceSnapShot> deviceSnapShot = deviceSnapShotRepository.findByImeiMsisdnIdentityImei(imei);
			return deviceSnapShot;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
			return null;
		}
	}

}
