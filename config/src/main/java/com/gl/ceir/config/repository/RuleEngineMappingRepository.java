package com.gl.ceir.config.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.RuleEngineMapping;

@Repository
public interface RuleEngineMappingRepository extends JpaRepository<RuleEngineMapping, Long>, 
JpaSpecificationExecutor<RuleEngineMapping> {

	public RuleEngineMapping getById(long id);

	public RuleEngineMapping getByName(String name);
	
	public RuleEngineMapping findByNameAndFeatureAndUserType(String name,String feature,String userType);

	public void deleteById(long id);
}
