package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.Action;
import org.gl.ceir.CeirPannelCode.config.Model.ActionParameters;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.ActionNames;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.ActionParametersName;
import org.gl.ceir.CeirPannelCode.config.Repository.ActionParametersRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.ActionRepository;
import org.gl.ceir.CeirPannelCode.config.Service.ActionParametersService;

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

	@Override
	public Map<ActionNames, Map<ActionParametersName, String>> findAllWithMap() {
		HashMap<ActionNames, Map<ActionParametersName, String>> mapOfActionsAndActionParameters = new HashMap<ActionNames, Map<ActionParametersName, String>>();
		try {
			List<ActionParameters> list = actionParametersRepository.findAll();
			for (ActionParameters actionParameters : list) {
				Map<ActionParametersName, String> value = mapOfActionsAndActionParameters
						.get(actionParameters.getAction().getName());

				if (value == null) {
					value = new HashMap<ActionParametersName, String>();
					mapOfActionsAndActionParameters.put(actionParameters.getAction().getName(), value);
				}
				value.put(actionParameters.getName(), actionParameters.getValue());
			}
			logger.info("mapOfActionsAndActionParameters : " + mapOfActionsAndActionParameters);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("ActionParametersService", e.getMessage());
		}
		return mapOfActionsAndActionParameters;
	}

}
