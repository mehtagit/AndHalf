package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.repository.ActionRepository;
import com.gl.ceir.config.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Override
	public List<Action> getAll() {
		return actionRepository.findAll();
	}

	@Override
	public Action save(Action action) {
		return actionRepository.save(action);
	}

	@Override
	public Action get(Long id) {
		Action action = actionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Action", "id", id));
		return action;
	}

	@Override
	public Action update(Action action) {
		return actionRepository.save(action);
	}

	@Override
	public void delete(Long id) {
		actionRepository.deleteById(id);
	}

}
