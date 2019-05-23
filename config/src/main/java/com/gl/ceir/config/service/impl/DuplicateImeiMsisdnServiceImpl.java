package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.repository.DuplicateImeiMsisdnRepository;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;

@Service
public class DuplicateImeiMsisdnServiceImpl implements DuplicateImeiMsisdnService {

	@Autowired
	private DuplicateImeiMsisdnRepository duplicateImeiMsisdnRepository;

	@Override
	public List<DuplicateImeiMsisdn> getAll() {
		return duplicateImeiMsisdnRepository.findAll();
	}

	@Override
	public DuplicateImeiMsisdn save(DuplicateImeiMsisdn duplicateImeiMsisdn) {
		return duplicateImeiMsisdnRepository.save(duplicateImeiMsisdn);
	}

	@Override
	public DuplicateImeiMsisdn get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DuplicateImeiMsisdn update(DuplicateImeiMsisdn duplicateImeiMsisdn) {
		return duplicateImeiMsisdnRepository.save(duplicateImeiMsisdn);
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

	@Override
	public DuplicateImeiMsisdn get(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		DuplicateImeiMsisdn duplicateImeiMsisdn = duplicateImeiMsisdnRepository.findById(imeiMsisdnIdentity)
				.orElseThrow(() -> new ResourceNotFoundException("Duplicate Imei Msisdn", "imeiMsisdnIdentity",
						imeiMsisdnIdentity));
		return duplicateImeiMsisdn;
	}

	@Override
	public void delete(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		duplicateImeiMsisdnRepository.deleteById(imeiMsisdnIdentity);
	}

}
