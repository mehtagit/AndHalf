package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.SmsAccount;
import com.gl.ceir.config.model.SmsScript;
import com.gl.ceir.config.repository.SmsScriptRepository;
import com.gl.ceir.config.service.SmsScriptService;

@Service
public class SmsScriptServiceImpl implements SmsScriptService {

	private static final Logger logger = LogManager.getLogger(SmsScriptServiceImpl.class);

	@Autowired
	private SmsScriptRepository smsScriptRepository;

	@Override
	public List<SmsScript> getAll() {

		try {
			return smsScriptRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public SmsScript save(SmsScript smsScript) {

		try {
			return smsScriptRepository.save(smsScript);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public SmsScript get(Long id) {

		try {
			SmsScript smsScript = smsScriptRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Sms Script", "id", id));
			return smsScript;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public SmsScript update(SmsScript smsScript) {

		try {
			return smsScriptRepository.save(smsScript);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {

		try {
			smsScriptRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

}
