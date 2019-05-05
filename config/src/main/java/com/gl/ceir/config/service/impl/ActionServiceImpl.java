package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.repository.ActionRepository;
import com.gl.ceir.config.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Override
	public List<Action> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action save(Action t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action update(Action t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub
		
	}

}
