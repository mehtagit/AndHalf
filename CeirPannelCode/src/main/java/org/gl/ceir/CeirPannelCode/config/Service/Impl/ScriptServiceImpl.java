package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.SmsAccount;
import org.gl.ceir.CeirPannelCode.config.Model.Script;
import org.gl.ceir.CeirPannelCode.config.Repository.SmsScriptRepository;
import org.gl.ceir.CeirPannelCode.config.Service.ScriptService;

@Service 
public class ScriptServiceImpl implements ScriptService {

	private static final Logger logger = LogManager.getLogger(ScriptServiceImpl.class);

	@Autowired
	private SmsScriptRepository smsScriptRepository;

	@Override
	public List<Script> getAll() {

		try {
			return smsScriptRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public Script save(Script smsScript) {

		try {
			return smsScriptRepository.save(smsScript);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public Script get(Long id) {

		try {
			Script smsScript = smsScriptRepository.findById(id)
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
	public Script update(Script smsScript) {

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
