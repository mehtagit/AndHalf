package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.SmsAccount;
import com.gl.ceir.config.model.SmsScript;
import com.gl.ceir.config.repository.SmsScriptRepository;
import com.gl.ceir.config.service.SmsScriptService;

@Service
public class SmsScriptServiceImpl implements SmsScriptService {

	@Autowired
	private SmsScriptRepository smsScriptRepository;

	@Override
	public List<SmsScript> getAll() {
		return smsScriptRepository.findAll();
	}

	@Override
	public SmsScript save(SmsScript smsScript) {
		return smsScriptRepository.save(smsScript);
	}

	@Override
	public SmsScript get(Long id) {
		SmsScript smsScript = smsScriptRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sms Script", "id", id));
		return smsScript;
	}

	@Override
	public SmsScript update(SmsScript smsScript) {
		return smsScriptRepository.save(smsScript);
	}

	@Override
	public void delete(Long id) {
		smsScriptRepository.deleteById(id);

	}

}
