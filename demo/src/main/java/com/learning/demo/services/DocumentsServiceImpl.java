package com.learning.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.learning.demo.exceptions.ResourceNotFoundException;
import com.learning.demo.repository.DocumentsByImeiMsisdnRepository;
import com.learning.demo.repository.DocumentsRepository;
import com.learning.demo.resource.Documents;
import com.learning.demo.resource.DocumentsByImeiMsisdn;
import com.learning.demo.resource.ImeiMsisdnIdentity;

public class DocumentsServiceImpl implements DocumentsService {

	private static final Logger logger = LogManager.getLogger(DocumentsService.class);

	@Autowired
	private DocumentsRepository documentsRepository;

	@Autowired
	DocumentsByImeiMsisdnRepository documentsByImeiMsisdnRepository;

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
	public Set<Documents> get(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		Optional<DocumentsByImeiMsisdn> optional = documentsByImeiMsisdnRepository.findById(imeiMsisdnIdentity);
		DocumentsByImeiMsisdn documentsByImeiMsisdn = optional.orElseThrow(
				() -> new ResourceNotFoundException("Action Parameters", "imeiMsisdnIdentity", imeiMsisdnIdentity));
		return documentsByImeiMsisdn.getDocuments();
	}

}
