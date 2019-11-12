package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gl.ceir.config.model.PolicyConfigurationHistoryDb;

public interface PolicyConfigurationHistoryDbRepository  extends JpaRepository<PolicyConfigurationHistoryDb, Long>{

}
