package org.gl.substation.serviceImpl;

import java.util.Optional;

import org.gl.substation.repository.PowerStationRepository;
import org.gl.substation.service.PowerStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public class PowerStationServiceImpl implements PowerStationService{

	/*
	 * @Autowired PowerStationRepository powerStationRepository;
	 * 
	 * @Override public Optional<Page<?>> list(int pageNo,int size,Sort.Direction
	 * direction) {
	 * 
	 * Pageable paging = PageRequest.of(pageNo, size,direction,"name"); Page<?> page
	 * = powerStationRepository.findAll(paging); Optional<Page<?>> optional
	 * =Optional.ofNullable(page);
	 * 
	 * if(optional.isPresent()) { return optional; } return null; }
	 */
}