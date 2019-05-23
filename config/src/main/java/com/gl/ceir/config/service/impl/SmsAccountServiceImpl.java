package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.model.SmsAccount;
import com.gl.ceir.config.repository.SmsAccountRepository;
import com.gl.ceir.config.service.SmsAccountService;

@Service
public class SmsAccountServiceImpl implements SmsAccountService {

	@Autowired
	private SmsAccountRepository smsAccountRepository;

	@Override
	public List<SmsAccount> getAll() {
		return smsAccountRepository.findAll();
	}

	@Override
	public SmsAccount save(SmsAccount smsAccount) {
		return smsAccountRepository.save(smsAccount);
	}

	@Override
	public SmsAccount get(Long id) {
		SmsAccount smsAccount = smsAccountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sms Account", "id", id));
		return smsAccount;
	}

	@Override
	public SmsAccount update(SmsAccount smsAccount) {
		return smsAccountRepository.save(smsAccount);
	}

	@Override
	public void delete(Long id) {
		smsAccountRepository.deleteById(id);
	}

}
