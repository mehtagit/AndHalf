package org.gl.substation.repository;

import java.util.List;

import org.gl.substation.entity.DashboardData;
import org.gl.substation.entity.ModemInfoTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface PowerStationRepository extends JpaRepository<ModemInfoTable, Long>{
	//@Query("SELECT p.substation FROM PowerStation p")
	//@Query("SELECT   NEW  org.gl.substation.entity.DashboardData (p.unitID,p.substation,c.lastIntervalPacketDate,c.lastFaultPacketDate) FROM PowerStation p,"+ 
			//"SubstationConfiguration c where p.unitID  = c.unitID ORDER BY p.substation ASC")
	
	@Query("SELECT   NEW  org.gl.substation.entity.DashboardData (p.id,p.modemId,p.portId,p.modem,p.statusint) FROM ModemInfoTable p ORDER BY p.modem ASC")
	List<DashboardData> substationNameList();
}
