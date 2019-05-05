package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.SmsScript;
import com.gl.ceir.config.repository.SmsScriptRepository;
import com.gl.ceir.config.service.SmsScriptService;

@Service
public class SmsScriptServiceImpl implements SmsScriptService {

	@Autowired
	private SmsScriptRepository smsScriptRepository;

	@Override
	public List<SmsScript> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsScript save(SmsScript t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsScript get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsScript update(SmsScript t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
