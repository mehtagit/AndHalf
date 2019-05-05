package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.MediationSource;
import com.gl.ceir.config.repository.MediationSourceRepository;
import com.gl.ceir.config.service.MediationSourceService;

@Service
public class MediationSourceServiceImpl implements MediationSourceService {

	@Autowired
	private MediationSourceRepository mediationSourceRepository;

	@Override
	public List<MediationSource> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediationSource save(MediationSource t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediationSource get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediationSource update(MediationSource t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
