package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gl.ceir.config.model.GrievanceMsg;

public interface GrievanceMsgRepository extends JpaRepository<GrievanceMsg, Long>, JpaSpecificationExecutor<GrievanceMsg> {
	public GrievanceMsg save( GrievanceMsg grievanceMsg );
	public List<GrievanceMsg> getGrievanceMsgByGrievanceId( Long grievanceId );
}
