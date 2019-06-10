package com.gl.ceir.config.service;

import java.util.List;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.SystemPolicyMapping;

public interface SystemPolicyMappingService extends RestServices<SystemPolicyMapping> {

	public List<SystemPolicyMapping> getSystemPolicies(Rules rule);
}
