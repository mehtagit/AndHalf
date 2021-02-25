package org.gl.substation.repository;

import java.util.List;
import java.util.Optional;

import org.gl.substation.entity.SubstationDefaultReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubstationDefaultReportRepository extends JpaRepository<SubstationDefaultReport, Long>{
	//public Optional<Page<?>> findByUnitId(String unitId, int pageNo, int size, Direction direction);

	public Page<Optional<List<SubstationDefaultReport>>> findAllByUnitId(String unitId, Pageable paging);
	
}
