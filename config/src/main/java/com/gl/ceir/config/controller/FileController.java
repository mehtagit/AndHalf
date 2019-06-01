package com.gl.ceir.config.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.DocumentStatus;
import com.gl.ceir.config.model.Documents;
import com.gl.ceir.config.model.UploadFileRequest;
import com.gl.ceir.config.model.UploadFileResponse;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.constants.DocumentType;
import com.gl.ceir.config.service.DocumentsService;
import com.gl.ceir.config.service.impl.FileStorageService;

import io.swagger.annotations.ApiOperation;

@RestController
public class FileController {

	private static final Logger logger = LogManager.getLogger(FileController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private DocumentsService documentService;

	@ApiOperation(value = "Upload file Ticket ID and Document Type must be there ", response = UploadFileResponse.class)
	@RequestMapping(path = "/document/updateStatus", method = RequestMethod.PATCH)
	public MappingJacksonValue partialUpdateName(@RequestParam String documentStatus, @RequestParam Long documentId) {

		DocumentStatus documentStatusEnum = DocumentStatus.getDocumentStatus(documentStatus);
		if (documentStatusEnum == null) {
			throw new ResourceNotFoundException("Status can be APPROVED / REJECTED", "id", documentId);
		} else {
			Documents documents = documentService.updateStatus(documentStatusEnum, documentId);
			MappingJacksonValue mapping = new MappingJacksonValue(documents);
			return mapping;
		}

	}

	@ApiOperation(value = "View all the list of documents for imei OR msisdn", response = VipList.class)
	@RequestMapping(path = "/document/", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@RequestParam(required = false) Long msisdn,
			@RequestParam(required = false) Long imei) {
		List<Documents> documents = documentService.getByMsisdnAndImei(imei, msisdn);
		MappingJacksonValue mapping = new MappingJacksonValue(documents);
		return mapping;
	}

	@ApiOperation(value = "View All Types of document can upload ", response = String.class, responseContainer = "list")
	@GetMapping("/document/types")
	public ResponseEntity<List<String>> getAllDocumentTypes() {
		return new ResponseEntity<>(DocumentType.getDocumentTypes(), HttpStatus.OK);
	}

	@ApiOperation(value = "Upload file Ticket ID and Document Type must be there ", response = UploadFileResponse.class)
	@RequestMapping(path = "/document/upload", method = RequestMethod.POST)
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, String ticketId, Long imei,
			Long msisdn, DocumentType documentType) {

		UploadFileRequest uploadFileRequest = new UploadFileRequest();
		uploadFileRequest.setDocumentType(documentType);
		uploadFileRequest.setTicketId(ticketId);
		uploadFileRequest.setMsisdn(msisdn);
		uploadFileRequest.setImei(imei);

		UploadFileResponse uploadFileResponse = fileStorageService.storeFile(file, uploadFileRequest);

		return uploadFileResponse;
	}

	@GetMapping(FileStorageService.downloadContext + "{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
