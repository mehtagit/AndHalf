package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gl.ceir.config.model.PolicyConfigurationDb;

public interface PolicyConfigurationDbRepository extends JpaRepository<PolicyConfigurationDb, Long> {

}
