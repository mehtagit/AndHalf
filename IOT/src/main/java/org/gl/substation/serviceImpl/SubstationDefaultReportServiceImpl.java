package org.gl.substation.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.gl.substation.entity.SubstationDefaultReport;
import org.gl.substation.model.SubstationDefaultRequestModel;
import org.gl.substation.repository.SubstationDefaultReportRepository;
import org.gl.substation.service.SubstationDefaultReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class SubstationDefaultReportServiceImpl implements SubstationDefaultReportService{

	@Autowired SubstationDefaultReportRepository substationDefaultReportRepository;

	@Override
	public Page<Optional<List<SubstationDefaultReport>>> findByUnitId(SubstationDefaultRequestModel substationDefaultRequestModel, int pageNo, int size,
			Direction direction) {
		 Pageable paging = PageRequest.of(pageNo, size,direction,"id");
		 Page<Optional<List<SubstationDefaultReport>>> list = substationDefaultReportRepository.findAllByUnitId(substationDefaultRequestModel.getUnitId(),paging);
		return list;
	}



}
