package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gl.ceir.config.model.Documents;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.repository.DocumentsRepository;
import com.gl.ceir.config.service.DocumentsService;

public class DocumentsServiceImpl implements DocumentsService {

	private static final Logger logger = LogManager.getLogger(DocumentsService.class);

	@Autowired
	private DocumentsRepository documentsRepository;

	@Override
	public Documents get(Long id) {
		Optional<Documents> documentOptional = documentsRepository.findById(id);
		if (documentOptional.isPresent())
			return documentOptional.get();
		else
			return null;
	}

	@Override
	public Documents save(Documents documents) {
		return documentsRepository.save(documents);
	}

	@Override
	public Documents updateStatus(Documents documents) {
		return documentsRepository.save(documents);
	}

	@Override
	public List<Documents> getByTicketId(String ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Documents> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

}
