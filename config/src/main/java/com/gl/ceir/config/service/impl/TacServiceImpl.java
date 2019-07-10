package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.Tac;
import com.gl.ceir.config.repository.TacRepository;
import com.gl.ceir.config.service.TacService;

@Service
public class TacServiceImpl implements TacService {
	private final Logger logger = LogManager.getLogger(SmsScriptServiceImpl.class);

	@Autowired
	private TacRepository tacRepository;

	@Override
	public List<Tac> getAll() {
		try {
			return tacRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	@Caching(put = { @CachePut(value = "tac", key = "#tac.id") })
	public Tac save(Tac tac) {
		try {
			return tacRepository.save(tac);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public Tac get(Long id) {
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Tac update(Tac tac) {
		try {
			return tacRepository.save(tac);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	@Cacheable(value = "tac", key = "#id")
	public Tac get(String id) {
		try {
			Tac tac = tacRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tac ", "id", id));
			return tac;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
