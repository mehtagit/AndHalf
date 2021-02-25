package org.gl.substation.rest.controller;

import java.util.List;
import java.util.Optional;

import org.gl.substation.entity.SubstationDefaultReport;
import org.gl.substation.model.SubstationDefaultRequestModel;
import org.gl.substation.serviceImpl.SubstationDefaultReportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin	
@RestController
@RequestMapping("/defaultReport")
public class SubstationDefaultReportController {
	@Autowired SubstationDefaultReportServiceImpl substationDefaultReportServiceImpl;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/get")
	public ResponseEntity<?> getSubstationDefaultReport(@RequestBody SubstationDefaultRequestModel substationDefaultRequestModel,@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "size", defaultValue = "10") Integer size, @RequestParam(value = "order", defaultValue = "DESC", required = false) Sort.Direction direction){
			Page<Optional<List<SubstationDefaultReport>>> response = substationDefaultReportServiceImpl.findByUnitId(substationDefaultRequestModel, pageNo, size, direction);
		logger.info("Controller --> /defaultReport/get  response ::"+response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
