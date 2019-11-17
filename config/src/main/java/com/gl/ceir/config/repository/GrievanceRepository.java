package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.Grievance;

public interface GrievanceRepository extends JpaRepository<Grievance, Long>, JpaSpecificationExecutor<Grievance>{
	
	public Grievance save(Grievance grievance);
	public List<Grievance> getGrievanceByUserId(Integer userId);
	//public Grievance getByGrievanceTxnId(String txnId);
	
}
