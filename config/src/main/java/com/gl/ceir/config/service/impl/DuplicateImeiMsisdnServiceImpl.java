package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
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
			return saveRedis(duplicateImeiMsisdn);
		} catch (org.springframework.data.redis.RedisConnectionFailureException redisException) {
			logger.warn("Device:" + duplicateImeiMsisdn.getImeiMsisdnIdentity()
					+ " Not saved in Cache for DuplicateImeiMsisdn");
			return get(duplicateImeiMsisdn.getImeiMsisdnIdentity());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Cacheable(value = "duplicateImeiMsisdn", key = "#duplicateImeiMsisdn.imeiMsisdnIdentity")
	private DuplicateImeiMsisdn saveRedis(DuplicateImeiMsisdn duplicateImeiMsisdn) {

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
			return getRedis(imeiMsisdnIdentity);
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (org.springframework.data.redis.RedisConnectionFailureException redisException) {
			try {
				DuplicateImeiMsisdn duplicateImeiMsisdn = duplicateImeiMsisdnRepository.findById(imeiMsisdnIdentity)
						.orElseThrow(() -> new ResourceNotFoundException("Duplicate Imei Msisdn", "imeiMsisdnIdentity",
								imeiMsisdnIdentity));
				return duplicateImeiMsisdn;
			} catch (ResourceNotFoundException e) {
				throw e;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Cacheable(value = "pendingActionsByMsisdnAndImei", key = "#imeiMsisdnIdentity")
	private DuplicateImeiMsisdn getRedis(ImeiMsisdnIdentity imeiMsisdnIdentity) {

		try {
			DuplicateImeiMsisdn duplicateImeiMsisdn = duplicateImeiMsisdnRepository.findById(imeiMsisdnIdentity)
					.orElseThrow(() -> new ResourceNotFoundException("Duplicate Imei Msisdn", "imeiMsisdnIdentity",
							imeiMsisdnIdentity));
			return duplicateImeiMsisdn;
		} catch (ResourceNotFoundException e) {
			throw e;
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

	@Override
	public List<DuplicateImeiMsisdn> saveAll(List<DuplicateImeiMsisdn> duplicateImeiMsisdns) {
		return duplicateImeiMsisdnRepository.saveAll(duplicateImeiMsisdns);
	}

}
