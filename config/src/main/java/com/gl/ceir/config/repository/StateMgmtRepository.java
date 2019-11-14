package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.StateMgmtDb;

@Repository
public interface StateMgmtRepository extends JpaRepository<StateMgmtDb, Long>, JpaSpecificationExecutor<StateMgmtDb> {

	@Query("select s from state_mgmt_db inner join fetch s.feature_id states_interpretation_db ")
	public List<StateMgmtDb> getByFeatureIdAndUserTypeId(Integer featureId, Integer userTypeId);

}
