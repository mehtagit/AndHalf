package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.WhiteList;
import com.gl.ceir.config.repository.WhiteListRepository;
import com.gl.ceir.config.service.WhiteListService;

@Service
public class WhiteListServiceImpl implements WhiteListService {

	private static final Logger logger = LogManager.getLogger(WhiteListServiceImpl.class);

	@Autowired
	private WhiteListRepository whiteListRepository;

	@Override
	public List<WhiteList> getAll() {
		try {
			return whiteListRepository.findAll();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public WhiteList save(WhiteList whiteList) {

		try {
			return whiteListRepository.save(whiteList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public WhiteList get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {

		try {
			whiteListRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public WhiteList update(WhiteList whiteList) {
		try {
			return whiteListRepository.save(whiteList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public WhiteList getByMsisdn(Long msisdn) {
		try {
			return whiteListRepository.findByMsisdn(msisdn);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public WhiteList getByImei(Long imei) {
		try {
			return whiteListRepository.findByImei(imei);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public WhiteList getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		logger.info("Going to get White List Devices by " + imeiMsisdnIdentity);
		try {
			if (imeiMsisdnIdentity.getMsisdn() == null && imeiMsisdnIdentity.getImei() == null) {
				return null;
			} else if (imeiMsisdnIdentity.getMsisdn() != null && imeiMsisdnIdentity.getImei() != null) {
				return whiteListRepository.findByMsisdnAndImei(imeiMsisdnIdentity.getMsisdn(),
						imeiMsisdnIdentity.getImei());
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
	public void deleteByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		logger.info("Going to delete White List Device by " + imeiMsisdnIdentity);
		if (imeiMsisdnIdentity.getMsisdn() != null && imeiMsisdnIdentity.getImei() != null) {
			whiteListRepository.deleteByMsisdnAndImei(imeiMsisdnIdentity.getMsisdn(), imeiMsisdnIdentity.getImei());
		} else {
			logger.info("Not Deleted " + imeiMsisdnIdentity);
			return;
		}
	}

}
