package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gl.ceir.config.model.ActionConfigDb;

@Repository
public interface ActionConfigRepository extends JpaRepository<ActionConfigDb, Long>, JpaSpecificationExecutor<ActionConfigDb> {

	public List<ActionConfigDb> getByStateId(long stateId);

}
