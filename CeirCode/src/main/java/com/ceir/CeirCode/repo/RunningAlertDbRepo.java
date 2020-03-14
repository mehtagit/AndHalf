package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.RunningAlertDb;

public interface RunningAlertDbRepo extends JpaRepository<RunningAlertDb, Long>{

	public RunningAlertDb save(RunningAlertDb alertDb);
}
