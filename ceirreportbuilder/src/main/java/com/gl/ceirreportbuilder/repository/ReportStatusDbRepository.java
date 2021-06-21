package com.gl.ceirreportbuilder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gl.ceirreportbuilder.model.ReportStatusDb;

@Repository
public interface ReportStatusDbRepository extends JpaRepository< ReportStatusDb, Long>{
	
	public ReportStatusDb save( ReportStatusDb reportStatusDb );
	
//	@Query(value="select r from ReportStatusDb r where r.scheduleFlag in(:scheduleFlag) and r.report.status =:status"
//			+ " and r.typeFlag =:typeFlag and r.rundate <= DATE(CURRENT_TIMESTAMP) and r.tryCount < :tryCount order by r.tryCount")
	@Query(value="select r from ReportStatusDb r where r.scheduleFlag in(:scheduleFlag) and r.report.status =:status"
			+ " and r.typeFlag =:typeFlag and (24 * (TO_DATE(TO_CHAR(CURRENT_TIMESTAMP,'YYYY-MM-DD hh24:mi'),'YYYY-MM-DD hh24:mi') "
			+ "- TO_DATE(TO_CHAR(r.rundate,'YYYY-MM-DD hh24:mi'),'YYYY-MM-DD hh24:mi'))) >= 1 "
			+ " and r.tryCount < :tryCount order by r.tryCount,r.reportnameId,r.rundate")
	public List<ReportStatusDb> findScheduleReports( @Param("scheduleFlag") List<Integer> scheduleFlag, @Param("status") Integer status,
			@Param("typeFlag") Integer typeFlag, @Param("tryCount") Integer tryCount);
	
	public List<ReportStatusDb> findByScheduleFlagAndTypeFlag(Integer scheduleFlag, Integer typeFlag);
}
