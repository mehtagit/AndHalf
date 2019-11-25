package com.gl.ceir.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.Grievance;
import com.gl.ceir.config.model.RequestCountAndQuantity;

import io.lettuce.core.dynamic.annotation.Param;

public interface GrievanceRepository extends JpaRepository<Grievance, Long>, JpaSpecificationExecutor<Grievance>{
	
	public Grievance save(Grievance grievance);
	public List<Grievance> getGrievanceByUserId(Integer userId);
	//public Grievance getByGrievanceTxnId(String txnId);
	public Grievance getBygrievanceId( String grievanceId );
	public List<Grievance> getAllGrievanceStatusNotClosed( Integer userId, Integer grievanceStatus );
	public List<Grievance> getAllGrievanceStatusNotClosedForAdmin( Integer grievanceStatus );
	
	@Query(value="select new com.gl.ceir.config.model.RequestCountAndQuantity(count(g.id) as count) from Grievance g "
			+ "where g.userId =:userId and g.grievanceStatus =:grievanceStatus")
	public RequestCountAndQuantity getGrievanceCount( @Param("userId") Integer userId, @Param("grievanceStatus") Integer grievanceStatus);
}
