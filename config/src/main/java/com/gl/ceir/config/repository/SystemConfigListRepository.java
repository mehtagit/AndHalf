package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gl.ceir.config.model.SystemConfigListDb;
import org.springframework.data.domain.Sort;

public interface SystemConfigListRepository extends CrudRepository<SystemConfigListDb, Long>, JpaRepository<SystemConfigListDb, Long> {
	
	public List<SystemConfigListDb> findByTag(String tag, Sort sort);
	
	@Query("SELECT DISTINCT a.tag FROM SystemConfigListDb a")
	List<String> findDistinctTags(); 
}
