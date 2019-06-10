package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.SystemPolicyMapping;

public interface SystemPolicyMappingRepository extends JpaRepository<SystemPolicyMapping, Long> {

	public List<SystemPolicyMapping> findByRules(Rules rules);
}
