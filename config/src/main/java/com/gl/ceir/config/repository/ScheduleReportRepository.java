package com.gl.ceir.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.gl.ceir.config.model.ScheduleReportDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ScheduleReportRepository extends JpaRepository<ScheduleReportDb, Long> {

    public Page<ScheduleReportDb> findAll(Specification<ScheduleReportDb> build, Pageable pageable);

//	public ScheduleReportDb findByName(ScheduleReportDb name);

}
