package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;
import java.util.Map;

import org.gl.ceir.CeirPannelCode.config.Model.ActionParameters;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.ActionNames;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.ActionParametersName;

public interface ActionParametersService extends RestServices<ActionParameters> {
	public List<ActionParameters> findByAction(Long action_id);

	public List<ActionParameters> findByAction(String actionName);

	public Map<ActionNames, Map<ActionParametersName, String>> findAllWithMap();
}
