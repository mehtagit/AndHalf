package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.repository.RulesRepository;
import com.gl.ceir.config.service.RulesService;

@Service
public class RulesServiceImpl implements RulesService {

	@Autowired
	private RulesRepository rulesRepository;

	@Override
	public List<Rules> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rules save(Rules t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rules get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rules update(Rules t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

}
