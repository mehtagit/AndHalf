package com.gl.ceir.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.MediationSource;
import com.gl.ceir.config.repository.MediationSourceRepository;
import com.gl.ceir.config.service.MediationSourceService;

@Service
public class MediationSourceServiceImpl implements MediationSourceService {

	@Autowired
	private MediationSourceRepository mediationSourceRepository;

	@Override
	public List<MediationSource> getAll() {
		return mediationSourceRepository.findAll();
	}

	@Override
	public MediationSource save(MediationSource mediationSource) {
		return mediationSourceRepository.save(mediationSource);
	}

	@Override
	public MediationSource get(Long id) {
		MediationSource mediationSource = mediationSourceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mediation Source", "id", id));
		return mediationSource;
	}

	@Override
	public MediationSource update(MediationSource mediationSource) {
		return mediationSourceRepository.save(mediationSource);
	}

	@Override
	public void delete(Long id) {
		mediationSourceRepository.deleteById(id);
	}

}
