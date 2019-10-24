package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

import org.gl.ceir.CeirPannelCode.config.Model.Rules;
import org.gl.ceir.CeirPannelCode.config.Model.SystemPolicyMapping;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.Period;

public interface SystemPolicyMappingService extends RestServices<SystemPolicyMapping> {

	public SystemPolicyMapping getSystemPolicies(Rules rule, Period period);

	public List<SystemPolicyMapping> getSystemPoliciesByPeriod(Period period);
}
