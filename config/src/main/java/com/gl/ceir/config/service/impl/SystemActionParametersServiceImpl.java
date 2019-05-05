package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.SystemActionParameters;
import com.gl.ceir.config.repository.SystemActionParamRepository;
import com.gl.ceir.config.service.SystemActionParametersService;

@Service
public class SystemActionParametersServiceImpl implements SystemActionParametersService {

	@Autowired
	private SystemActionParamRepository systemActionParamRepository;

	@Override
	public List<SystemActionParameters> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemActionParameters save(SystemActionParameters t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemActionParameters get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemActionParameters update(SystemActionParameters t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
