package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.repository.DuplicateImeiMsisdnRepository;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;

@Service
public class DuplicateImeiMsisdnServiceImpl implements DuplicateImeiMsisdnService {

	private static final Logger logger = LogManager.getLogger(DuplicateImeiMsisdnServiceImpl.class);

	@Autowired
	private DuplicateImeiMsisdnRepository duplicateImeiMsisdnRepository;

	@Override
	public List<DuplicateImeiMsisdn> getAll() {

		try {
			return duplicateImeiMsisdnRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public DuplicateImeiMsisdn save(DuplicateImeiMsisdn duplicateImeiMsisdn) {

		try {
			return duplicateImeiMsisdnRepository.save(duplicateImeiMsisdn);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public DuplicateImeiMsisdn get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DuplicateImeiMsisdn update(DuplicateImeiMsisdn duplicateImeiMsisdn) {

		try {
			return duplicateImeiMsisdnRepository.save(duplicateImeiMsisdn);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

	@Override
	public DuplicateImeiMsisdn get(ImeiMsisdnIdentity imeiMsisdnIdentity) {

		try {
			DuplicateImeiMsisdn duplicateImeiMsisdn = duplicateImeiMsisdnRepository.findById(imeiMsisdnIdentity)
					.orElseThrow(() -> new ResourceNotFoundException("Duplicate Imei Msisdn", "imeiMsisdnIdentity",
							imeiMsisdnIdentity));
			return duplicateImeiMsisdn;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		try {
			duplicateImeiMsisdnRepository.deleteById(imeiMsisdnIdentity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

}
