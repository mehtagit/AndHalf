package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ActionParametersNotFoundException;
import com.gl.ceir.config.model.ActionParameters;
import com.gl.ceir.config.repository.ActionParametersRepository;
import com.gl.ceir.config.service.ActionParametersService;

@Service
public class ActionParametersServiceImpl implements ActionParametersService {

	@Autowired
	ActionParametersRepository actionParametersRepository;

	@Override
	public List<ActionParameters> getAll() {
		return actionParametersRepository.findAll();
	}

	@Override
	public ActionParameters save(ActionParameters actionParameters) {

		return actionParametersRepository.save(actionParameters);
	}

	@Override
	public ActionParameters get(Long id) throws ActionParametersNotFoundException {
		ActionParameters actionParameters = actionParametersRepository.findById(id)
				.orElseThrow(() -> new ActionParametersNotFoundException("ActionParameters", "id", id));
		return actionParameters;
	}



	@Override
	public ActionParameters update(ActionParameters t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub
		
	}

}
