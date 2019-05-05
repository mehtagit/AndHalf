package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.SmsAccount;
import com.gl.ceir.config.repository.SmsAccountRepository;
import com.gl.ceir.config.service.SmsAccountService;

@Service
public class SmsAccountServiceImpl implements SmsAccountService {

	@Autowired
	private SmsAccountRepository smsAccountRepository;

	@Override
	public List<SmsAccount> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsAccount save(SmsAccount t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsAccount get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsAccount update(SmsAccount t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
