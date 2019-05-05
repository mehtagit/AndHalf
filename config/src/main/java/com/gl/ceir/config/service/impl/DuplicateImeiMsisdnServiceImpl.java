package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.repository.DuplicateImeiMsisdnRepository;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;

@Service
public class DuplicateImeiMsisdnServiceImpl implements DuplicateImeiMsisdnService {

	@Autowired
	private DuplicateImeiMsisdnRepository duplicateImeiMsisdnRepository;

	@Override
	public List<DuplicateImeiMsisdn> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DuplicateImeiMsisdn save(DuplicateImeiMsisdn t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DuplicateImeiMsisdn get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DuplicateImeiMsisdn update(DuplicateImeiMsisdn t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
