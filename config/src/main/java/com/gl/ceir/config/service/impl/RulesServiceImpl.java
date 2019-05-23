package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.repository.RulesRepository;
import com.gl.ceir.config.service.RulesService;

@Service
public class RulesServiceImpl implements RulesService {

	@Autowired
	private RulesRepository rulesRepository;

	@Override
	public List<Rules> getAll() {
		return rulesRepository.findAll();
	}

	@Override
	public Rules save(Rules rules) {
		return rulesRepository.save(rules);
	}

	@Override
	public Rules get(Long id) {
		Rules rules = rulesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rules", "id", id));
		return rules;
	}

	@Override
	public Rules update(Rules rules) {
		return rulesRepository.save(rules);
	}

	@Override
	public void delete(Long rules) {
		rulesRepository.deleteById(rules);
	}

}
