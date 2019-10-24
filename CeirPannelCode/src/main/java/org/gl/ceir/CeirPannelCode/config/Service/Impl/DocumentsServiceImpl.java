package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.Model.DocumentStatus;
import org.gl.ceir.CeirPannelCode.config.Model.Documents;
import org.gl.ceir.CeirPannelCode.config.Repository.DocumentsRepository;
import org.gl.ceir.CeirPannelCode.config.Service.DocumentsService;

@Service
public class DocumentsServiceImpl implements DocumentsService {

	private static final Logger logger = LogManager.getLogger(DocumentsService.class);

	@Autowired
	private DocumentsRepository documentsRepository;

	@Override
	public Documents get(Long id) {
		return documentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No document found for this ID", "id", id));
	}

	@Override
	public Documents save(Documents documents) {
		return documentsRepository.save(documents);
	}

	@Override
	public Documents updateStatus(DocumentStatus documentStatus, Long id) {
		Documents documents = documentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No document found for this ID", "id", id));
		documents.setStatus(documentStatus);

		return documentsRepository.save(documents);
	}

	@Override
	public List<Documents> getByMsisdnAndImei(Long imei, Long msisdn) {
		if (imei == null && msisdn == null) {
			throw new ResourceNotFoundException("To Get Document need Imei Or msisdn", "imei / msisdn",
					imei + "/" + msisdn);
		}

		return documentsRepository.findByImeiOrMsisdn(imei, msisdn);
	}

}
