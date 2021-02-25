package org.gl.substation.service;

import java.util.List;
import java.util.Optional;

import org.gl.substation.entity.SubstationDefaultReport;
import org.gl.substation.model.SubstationDefaultRequestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public interface SubstationDefaultReportService {
	public Page<Optional<List<SubstationDefaultReport>>> findByUnitId(SubstationDefaultRequestModel substationDefaultRequestModel,int pageNo,int size,Sort.Direction direction);
}
