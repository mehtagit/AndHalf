package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.Model.Rules;
import org.gl.ceir.CeirPannelCode.config.Model.SystemPolicyMapping;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.Period;
import org.gl.ceir.CeirPannelCode.config.Repository.SystemPolicyMappingRepository;
import org.gl.ceir.CeirPannelCode.config.Service.SystemPolicyMappingService;

@Service
public class SystemPolicyMappingServiceImpl implements SystemPolicyMappingService {

	@Autowired
	private SystemPolicyMappingRepository systemPolicyMappingRepository;

	@Override
	public List<SystemPolicyMapping> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemPolicyMapping save(SystemPolicyMapping t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemPolicyMapping get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemPolicyMapping update(SystemPolicyMapping t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long t) {
		// TODO Auto-generated method stub

	}

	@Override
	public SystemPolicyMapping getSystemPolicies(Rules rule, Period period) {
		return systemPolicyMappingRepository.findByRuleAndPeriodOrderByPriority(rule, period);
	}

	@Override
	public List<SystemPolicyMapping> getSystemPoliciesByPeriod(Period period) {
		return systemPolicyMappingRepository.findByPeriodOrderByPriority(period);
	}

}
