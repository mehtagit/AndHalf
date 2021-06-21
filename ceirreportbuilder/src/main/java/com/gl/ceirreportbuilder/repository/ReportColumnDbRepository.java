package com.gl.ceirreportbuilder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.ceirreportbuilder.model.ReportColumnDb;

@Repository
public interface ReportColumnDbRepository extends JpaRepository< ReportColumnDb, Long>{
	
	public List<ReportColumnDb> findByReportnameId( Long reportnameId);
	
	public List<ReportColumnDb> findByReportnameIdAndTypeFlagOrderByColumnOrderAsc( Long reportnameId, Integer typeFlag);
	
	public List<ReportColumnDb> findByReportnameIdAndTypeFlag( Long reportnameId, Integer typeFlag);
	
}
