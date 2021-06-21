package com.gl.ceirreportbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.ceirreportbuilder.model.ReportFreqDb;

@Repository
public interface ReportFreqDbRepository extends JpaRepository<ReportFreqDb, Long>{

}
