package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ceir.config.model.SystemConfigListDb;
import org.springframework.data.domain.Sort;

public interface SystemConfigListRepository extends JpaRepository<SystemConfigListDb, Long> {
	
	public List<SystemConfigListDb> findByTag(String tag, Sort sort);
}
