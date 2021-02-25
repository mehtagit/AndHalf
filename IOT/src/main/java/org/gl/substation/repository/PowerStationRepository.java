package org.gl.substation.repository;

import java.util.List;
import java.util.Optional;

import org.gl.substation.entity.DashboardData;
import org.gl.substation.entity.PowerStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface PowerStationRepository extends JpaRepository<PowerStation, Long>{
	//@Query("SELECT p.substation FROM PowerStation p")
	@Query("SELECT   NEW  org.gl.substation.entity.DashboardData (p.unitID,p.substation,c.lastIntervalPacketDate,c.lastFaultPacketDate) FROM PowerStation p,"+ 
			"SubstationConfiguration c where p.unitID  = c.unitID ORDER BY p.substation ASC")
	List<DashboardData> substationNameList();
}
