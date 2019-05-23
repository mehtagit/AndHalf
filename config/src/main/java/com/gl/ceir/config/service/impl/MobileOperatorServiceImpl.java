package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.repository.MobileOperatorRepository;
import com.gl.ceir.config.service.MobileOperatorService;

@Service
public class MobileOperatorServiceImpl implements MobileOperatorService {

	@Autowired
	private MobileOperatorRepository mobileOperatorRepository;

	@Override
	public List<MobileOperator> getAll() {
		return mobileOperatorRepository.findAll();
	}

	@Override
	public MobileOperator save(MobileOperator mobileOperator) {
		// TODO Auto-generated method stub
		return mobileOperatorRepository.save(mobileOperator);
	}

	@Override
	public MobileOperator get(Long id) {
		MobileOperator mobileOperator = mobileOperatorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mobile Operator", "id", id));
		return mobileOperator;
	}

	@Override
	public void delete(Long id) {
		mobileOperatorRepository.deleteById(id);
	}

	@Override
	public MobileOperator update(MobileOperator t) {
		return mobileOperatorRepository.save(t);
	}

}
