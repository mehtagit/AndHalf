package com.gl.ceirreportbuilder.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gl.ceirreportbuilder.model.SystemConfigListDb;

//@Component
@Repository
public interface SystemConfigListRepository extends CrudRepository<SystemConfigListDb, Long>, 
JpaRepository<SystemConfigListDb, Long>, JpaSpecificationExecutor<SystemConfigListDb>{
	
	public List<SystemConfigListDb> findByTag(String tag, Sort sort);
	
//	@Query("SELECT DISTINCT a.tag FROM SystemConfigListDb a")
//	List<String> findDistinctTags();
//	
//	@Query("SELECT NEW com.gl.ceir.config.model.SystemConfigListDb(a.tag, a.description, a.displayName) FROM SystemConfigListDb a group by a.tag, a.description, a.displayName")
//	List<SystemConfigListDb> findDistinctTagsWithDescription();
	
	public SystemConfigListDb getById(long id);
	
}
