package com.gl.ceir.config.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.model.DocumentType;
import com.gl.ceir.config.model.UploadFileRequest;
import com.gl.ceir.config.model.UploadFileResponse;
import com.gl.ceir.config.service.impl.FileStorageService;

import io.swagger.annotations.ApiOperation;

@RestController
public class FileController {

	private static final Logger logger = LogManager.getLogger(FileController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@ApiOperation(value = "View All Types of document can upload ", response = String.class, responseContainer = "list")
	@GetMapping("/document/types")
	public ResponseEntity<List<String>> getAllDocumentTypes() {
		return new ResponseEntity<>(DocumentType.getDocumentTypes(), HttpStatus.OK);
	}

	@ApiOperation(value = "Upload file Ticket ID and Document Type must be there ", response = UploadFileResponse.class)
	@RequestMapping(path = "/document/upload", method = RequestMethod.POST)
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, String ticketId,
			DocumentType documentType) {

		UploadFileRequest uploadFileRequest = new UploadFileRequest();
		uploadFileRequest.setDocumentType(documentType);
		uploadFileRequest.setTicketId(ticketId);

		UploadFileResponse uploadFileResponse = fileStorageService.storeFile(file, uploadFileRequest);

		return uploadFileResponse;
	}

	@GetMapping("/document/download/{fileName:.+}")
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
