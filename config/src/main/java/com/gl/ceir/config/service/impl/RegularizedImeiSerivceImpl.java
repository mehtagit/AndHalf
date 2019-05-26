package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.RegularizedImei;
import com.gl.ceir.config.repository.RegularizedImeiRepository;
import com.gl.ceir.config.service.RegularizedImeiSerivce;

@Service
public class RegularizedImeiSerivceImpl implements RegularizedImeiSerivce {

	private static final Logger logger = LogManager.getLogger(RegularizedImeiSerivceImpl.class);

	@Autowired
	private RegularizedImeiRepository regularizedImeiRepository;

	@Override
	public List<RegularizedImei> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegularizedImei save(RegularizedImei t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegularizedImei get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegularizedImei update(RegularizedImei t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
