package com.gl.ceir.config.service;

import java.util.List;

import com.gl.ceir.config.model.ActionParameters;

public interface ActionParametersService extends RestServices<ActionParameters> {
	public List<ActionParameters> findByAction(Long action_id);

	public List<ActionParameters> findByAction(String actionName);
}
