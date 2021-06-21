package com.gl.ceirreportbuilder.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.ceirreportbuilder.model.Scheduler;

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Long>{
	public Scheduler findBySchedulerName( String schedulerName);
	public Scheduler save( Scheduler scheduler);
}
