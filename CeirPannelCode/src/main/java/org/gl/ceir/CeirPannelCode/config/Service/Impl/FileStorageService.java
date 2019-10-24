package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.gl.ceir.CeirPannelCode.config.Configuration.FileStorageProperties;
import org.gl.ceir.CeirPannelCode.config.controller.FileController;
import org.gl.ceir.CeirPannelCode.config.exceptions.FileStorageException;
import org.gl.ceir.CeirPannelCode.config.exceptions.MyFileNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.Model.DocumentStatus;
import org.gl.ceir.CeirPannelCode.config.Model.Documents;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.PendingActions;
import org.gl.ceir.CeirPannelCode.config.Model.UploadFileRequest;
import org.gl.ceir.CeirPannelCode.config.Model.UploadFileResponse;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.TransactionState;
import org.gl.ceir.CeirPannelCode.config.Repository.PendingActionsRepositoy;
import org.gl.ceir.CeirPannelCode.config.Service.DocumentsService;
import org.gl.ceir.CeirPannelCode.config.Service.PendingActionsService;

@Service
public class FileStorageService {
	private static final Logger logger = LogManager.getLogger(FileStorageService.class);

	public static final String downloadContext = "/document/download/";

	private final Path fileStorageLocation;

	@Autowired
	private DocumentsService documentsService;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	private PendingActionsRepositoy pendingActionsRepositoy;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		System.out.println("fileStorageProperties " + fileStorageProperties.getUploadDir());
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	@Transactional
	public UploadFileResponse storeFile(MultipartFile file, UploadFileRequest uploadFileRequest) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Long imei = null;
		Long msisdn = null;

		if (uploadFileRequest.getTicketId() != null) {
			try {
				PendingActions pendingActions = pendingActionsService.get(uploadFileRequest.getTicketId());

				imei = pendingActions.getImei();
				msisdn = pendingActions.getMsisdn();
			} catch (ResourceNotFoundException e) {

				if (uploadFileRequest.getMsisdn() == null && uploadFileRequest.getImei() == null)
					throw e;
				else if (uploadFileRequest.getMsisdn() == null || uploadFileRequest.getImei() == null)
					throw new ResourceNotFoundException("To Upload file IMEI and MSISDN both required ",
							"IMEI / MSISDN", imei + "/" + msisdn);
				else {
					// TODO Need to validate imei and msisdn
					imei = uploadFileRequest.getImei();
					msisdn = uploadFileRequest.getMsisdn();
				}
			}
		} else {
			if (uploadFileRequest.getMsisdn() == null || uploadFileRequest.getImei() == null)
				throw new ResourceNotFoundException("To Upload file IMEI and MSISDN both required ", "IMEI / MSISDN",
						imei + "/" + msisdn);
			else {
				// TODO Need to validate imei and msisdn
				imei = uploadFileRequest.getImei();
				msisdn = uploadFileRequest.getMsisdn();
			}
		}

		String fileType = file.getContentType().split("/")[1];
		String newFileName = imei + "_" + msisdn + uploadFileRequest.getDocumentType().toString() + "." + fileType;
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(newFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(downloadContext)
					.path(newFileName).toUriString();

			Documents documents = new Documents();
			documents.setApprovalDate(new Date());
			documents.setDocumentType(uploadFileRequest.getDocumentType());
			documents.setFileUri(fileDownloadUri);
			documents.setFilename(newFileName);
			documents.setFileType(fileType);
			documents.setStatus(DocumentStatus.PENDING);
			documents.setMsisdn(msisdn);
			documents.setImei(imei);
			documents.setTicketId(uploadFileRequest.getTicketId());

			logger.info(documents);
			Documents savedDocument = documentsService.save(documents);
			if (uploadFileRequest.getTicketId() != null)
				pendingActionsService.changeTransactionState(uploadFileRequest.getTicketId(),
						TransactionState.WAIT_FOR_DOCUMENT_APPROVAL);
			logger.info("Document Saved Successfully" + savedDocument);

			return new UploadFileResponse(newFileName, fileDownloadUri, file.getContentType(), file.getSize());

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
}
