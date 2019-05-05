package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.SystemActionParameters;

public interface SystemActionParamRepository extends JpaRepository<SystemActionParameters, Long> {

}
