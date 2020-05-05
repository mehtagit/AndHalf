package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.ConfigurationManagement;

public interface ConfigurationManagementRepository extends JpaRepository<ConfigurationManagement, Long>,
JpaSpecificationExecutor<ConfigurationManagement> {


	public ConfigurationManagement getByName(String name);




}
