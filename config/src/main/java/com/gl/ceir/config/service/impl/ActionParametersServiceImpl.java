package com.gl.ceir.config.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.model.ActionParameters;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.repository.ActionParametersRepository;
import com.gl.ceir.config.repository.ActionRepository;
import com.gl.ceir.config.service.ActionParametersService;

@Service
public class ActionParametersServiceImpl implements ActionParametersService {

	private static final Logger logger = LogManager.getLogger(ActionParametersServiceImpl.class);
	@Autowired
	ActionParametersRepository actionParametersRepository;

	@Autowired
	ActionRepository actionRepository;

	@Override
	public List<ActionParameters> getAll() {
		try {
			return actionParametersRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}
	}

	@Override
	public ActionParameters save(ActionParameters actionParameters) {
		try {
			return actionParametersRepository.save(actionParameters);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}

	}

	@Override
	public ActionParameters get(Long id) throws ResourceNotFoundException {
		try {
			ActionParameters actionParameters = actionParametersRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Action Parameters", "id", id));
			return actionParameters;
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}
	}

	@Override
	public ActionParameters update(ActionParameters actionParameters) {
		try {
			return actionParametersRepository.save(actionParameters);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}

	}

	@Override
	public void delete(Long id) {
		try {
			actionParametersRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}

	}

	@Override
	public List<ActionParameters> findByAction(Long action_id) {
		try {
			Action action = actionRepository.findById(action_id)
					.orElseThrow(() -> new ResourceNotFoundException("Action", "action_id", action_id));
			return actionParametersRepository.findByAction(action);
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}

	}

	@Override
	public List<ActionParameters> findByAction(String name) {
		try {

			ActionNames actionNames = ActionNames.getActionNames(name);

			if (actionNames == null)
				throw new ResourceNotFoundException("Action", "actionName", name);

			Action action = actionRepository.findByName(actionNames);

			return actionParametersRepository.findByAction(action);
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}
	}

}
