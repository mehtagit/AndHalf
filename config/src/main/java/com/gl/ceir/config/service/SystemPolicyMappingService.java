package com.gl.ceir.config.service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.model.constants.Period;

public interface SystemPolicyMappingService extends RestServices<SystemPolicyMapping> {

	public SystemPolicyMapping getSystemPolicies(Rules rule, Period period);
}
