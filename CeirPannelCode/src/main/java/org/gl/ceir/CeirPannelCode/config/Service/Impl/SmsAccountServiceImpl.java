package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.MobileOperator;
import org.gl.ceir.CeirPannelCode.config.Model.SmsAccount;
import org.gl.ceir.CeirPannelCode.config.Repository.SmsAccountRepository;
import org.gl.ceir.CeirPannelCode.config.Service.SmsAccountService;

@Service
public class SmsAccountServiceImpl implements SmsAccountService {

	private static final Logger logger = LogManager.getLogger(SmsAccountServiceImpl.class);

	@Autowired
	private SmsAccountRepository smsAccountRepository;

	@Override
	public List<SmsAccount> getAll() {
		try {
			return smsAccountRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	@Override
	public SmsAccount save(SmsAccount smsAccount) {

		try {
			return smsAccountRepository.save(smsAccount);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public SmsAccount get(Long id) {

		try {
			SmsAccount smsAccount = smsAccountRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Sms Account", "id", id));
			return smsAccount;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public SmsAccount update(SmsAccount smsAccount) {

		try {
			return smsAccountRepository.save(smsAccount);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {

		try {
			smsAccountRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
