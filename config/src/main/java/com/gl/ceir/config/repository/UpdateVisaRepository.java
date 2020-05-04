package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.VisaUpdateDb;

public interface UpdateVisaRepository extends JpaRepository<VisaUpdateDb, Long>,JpaSpecificationExecutor<VisaUpdateDb>{


	public VisaUpdateDb save(VisaUpdateDb visa);
	public VisaUpdateDb findByUserId(long id);
	public VisaUpdateDb getById(long id);
	public VisaUpdateDb getByUserId(long id);
}
