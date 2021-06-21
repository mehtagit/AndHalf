package com.gl.ceirreportbuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.ceirreportbuilder.model.ReportDb;

@Repository
public interface ReportDbRepository extends JpaRepository< ReportDb, Long>{

}
