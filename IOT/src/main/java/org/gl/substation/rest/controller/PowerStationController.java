package org.gl.substation.rest.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.gl.substation.entity.DashboardData;
import org.gl.substation.repository.PowerStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin	
@RestController
@RequestMapping("/station")
public class PowerStationController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired PowerStationRepository powerStationRepository;

	@GetMapping("/get")
	public ResponseEntity<?> get(){
		logger.info("Inside substation controller");
		List<DashboardData> data=powerStationRepository.substationNameList();
		Optional<List<DashboardData>> list = Optional.ofNullable(data);
		logger.info("substation name list is ::::::::::"+list);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
