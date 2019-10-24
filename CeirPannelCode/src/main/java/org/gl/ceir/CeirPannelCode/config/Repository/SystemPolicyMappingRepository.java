package org.gl.ceir.CeirPannelCode.config.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.Rules;
import org.gl.ceir.CeirPannelCode.config.Model.SystemPolicyMapping;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.Period;

public interface SystemPolicyMappingRepository extends JpaRepository<SystemPolicyMapping, Long> {

	public SystemPolicyMapping findByRuleAndPeriodOrderByPriority(Rules rule, Period period);
	
	public List<SystemPolicyMapping> findByPeriodOrderByPriority(Period period);
}
