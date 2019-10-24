package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.soap.Text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.gl.ceir.CeirPannelCode.config.Configuration.FileStorageProperties;
import org.gl.ceir.CeirPannelCode.config.exceptions.FileStorageException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.Consignment;
import org.gl.ceir.CeirPannelCode.config.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.config.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.config.Model.StockDetailsOperation;
import org.gl.ceir.CeirPannelCode.config.Model.StokeDetails;
import org.gl.ceir.CeirPannelCode.config.Repository.ConsignmentRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.StockDetailsOperationRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.StokeDetailsRepository;

import java.io.*; 
import java.nio.file.Files; 
import java.nio.file.*;
@Service
public class ConsignmentServiceImpl {

	private static final Logger logger = LogManager.getLogger(ConsignmentServiceImpl.class);

	//private final Path fileStorageLocation;

	private final static String NUMERIC_STRING = "0123456789";

	@Autowired
	private ConsignmentRepository consignmentRepository;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired	
	StockDetailsOperationRepository stockDetailsOperationRepository;



	@Transactional
	public GenricResponse storeFile(String filePth, Consignment consignmentFileRequest) {

		try {
			String txnId=getTxnId();

			String serverPath=fileStorageProperties.getStokeUploadDir();
			serverPath = serverPath.replace("txnId", txnId);

			File dir = new File(serverPath);
			if (!dir.exists()) {
				boolean status=	dir.mkdirs();
			}
			consignmentFileRequest.setTxnId(txnId);

			logger.info("Server Path="+serverPath+"Old PAth="+filePth);

			Path temp = Files.move(Paths.get(filePth),Paths.get(serverPath+"/"+consignmentFileRequest.getFileName()));


			consignmentRepository.save(consignmentFileRequest);

			return new GenricResponse(200,"Upload Successfully");

		}catch (Exception e) {
			logger.error("Not Store File="+e.getMessage());

			throw new FileStorageException("Could not store file " + consignmentFileRequest.getFileName() + ". Please try again!", e);
		}
	}



	public String getTxnId() {

		DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
		//DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		Date date = new Date();
		String transactionId = dateFormat.format(date)+randomNumericString(3);	
		return transactionId;
	}

	public static String randomNumericString(int length) {
		StringBuilder builder = new StringBuilder();
		while (length-- != 0) {
			int character = (int)(Math.random()*NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}


	public List<Consignment> getAll(Long importerId) {
		try {
			logger.info("Going to get All Cosignment List ");
			return consignmentRepository.getByImporterIdOrderByIdDesc(importerId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}


	public Consignment getRecordInfo(String txnId) {
		try {
			logger.info("Going to get  Cosignment Record Info");
			return consignmentRepository.getByTxnId(txnId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}



	@Transactional
	public GenricResponse updatestoreFile(String fileName,String path, Consignment consignmentFileRequest) {

		try {

			Consignment consignmentInfo = consignmentRepository.getByTxnId(consignmentFileRequest.getTxnId());
			logger.info("ConsignmentInfo="+consignmentInfo.toString());


			if(consignmentInfo == null) {
				return new GenricResponse(1001,"Consignment Does Not exist.");
			}

			if(path == null || path.equalsIgnoreCase(" ") || path.isEmpty()) {

				consignmentFileRequest.setFileName(consignmentInfo.getFileName());
				consignmentFileRequest.setFileStatus(consignmentInfo.getFileStatus());
				consignmentFileRequest.setCreatedOn(consignmentInfo.getCreatedOn());
				consignmentFileRequest.setModifiedOn(new Date());
				consignmentFileRequest.setConsignmentStatus(consignmentInfo.getConsignmentStatus());

				consignmentRepository.updateConsignment(consignmentFileRequest.getConsignmentNumber(), consignmentFileRequest.getImporterName(), consignmentFileRequest.getSupplierId(), consignmentFileRequest.getSupplierName(),
						consignmentFileRequest.getExpectedArrivalPort(), consignmentFileRequest.getExpectedArrivaldate(),consignmentFileRequest.getExpectedDispatcheDate(), consignmentFileRequest.getOrganisationCountry(),consignmentFileRequest.getQuantity(),consignmentFileRequest.getTxnId());
				logger.info("FIle Null save succfully");	

				return new GenricResponse(200,"Update Successfully.");
			}else {
				consignmentFileRequest.setFileName(fileName);
				consignmentFileRequest.setFileStatus("INIT");
				consignmentFileRequest.setCreatedOn(consignmentInfo.getCreatedOn());
				consignmentFileRequest.setModifiedOn(new Date());
				consignmentFileRequest.setConsignmentStatus("Uploading");

				consignmentRepository.updateConsignmentFileStatus(consignmentFileRequest.getConsignmentNumber(), consignmentFileRequest.getImporterName(), consignmentFileRequest.getSupplierId(), consignmentFileRequest.getSupplierName(),
						consignmentFileRequest.getExpectedArrivalPort(), consignmentFileRequest.getExpectedArrivaldate(),consignmentFileRequest.getExpectedDispatcheDate(), consignmentFileRequest.getOrganisationCountry(), 
						"Uploading",fileName,"INIT",consignmentFileRequest.getQuantity(),consignmentFileRequest.getTxnId());
				//logger.info("FIle Null save succfully");
				//Consignment consignment = consignmentRepository.getByTxnId(consignmentFileRequest.getTxnId());

				List<StokeDetails> stokrDetails = stokeDetailsRepository.findByTxnIdAndSourceType(consignmentFileRequest.getTxnId(), "Importer");

				for(StokeDetails detials: stokrDetails) {			

					StockDetailsOperation stockOperation = new StockDetailsOperation();
					stockOperation.setDeviceNumber(detials.getDeviceNumber());
					stockOperation.setDeviceType(detials.getDeviceType());
					stockOperation.setFileName(consignmentInfo.getFileName());
					stockOperation.setImei(detials.getImei());
					stockOperation.setFileStatus(consignmentInfo.getFileStatus());
					stockOperation.setImeiAction(detials.getImeiAction());
					stockOperation.setOperation("Delete");
					stockOperation.setSourceType("Importer");
					stockOperation.setTxnId(consignmentFileRequest.getTxnId());
					stockOperation.setUserId(consignmentInfo.getImporterId());
					stockOperation.setUserName(consignmentInfo.getImporterName());
					stockOperation.setCreatedOn(new Date());
					stockOperation.setUpdatedOn(new Date());
					stockOperation.setTaxPaidStatus(consignmentInfo.getTaxPaidStatus());

					stockDetailsOperationRepository.save(stockOperation);
				}


				stokeDetailsRepository.deleteByTxnId(consignmentFileRequest.getTxnId());

				String serverPath=fileStorageProperties.getStokeUploadDir();
				serverPath = serverPath.replace("txnId", consignmentFileRequest.getTxnId());

				logger.info("Server Path="+serverPath);
				logger.info("Old Path="+path);
				File dir = new File(serverPath);

				if (!dir.exists()) {
					boolean status=	dir.mkdirs();
				}

				Path temp = Files.move(Paths.get(path),Paths.get(serverPath+"/"+fileName));

				consignmentRepository.updateConsignmentFileStatus(consignmentFileRequest.getConsignmentNumber(), consignmentFileRequest.getImporterName(), consignmentFileRequest.getSupplierId(), consignmentFileRequest.getSupplierName(),
						consignmentFileRequest.getExpectedArrivalPort(), consignmentFileRequest.getExpectedArrivaldate(),consignmentFileRequest.getExpectedDispatcheDate(), consignmentFileRequest.getOrganisationCountry(),
						"Uploading",consignmentFileRequest.getFileName(),"INIT",consignmentFileRequest.getQuantity(),consignmentFileRequest.getTxnId());



				return new GenricResponse(200,"Update Successfully.");

			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	} 

	@Transactional
	public GenricResponse deleteConsigmentInfo(String txnId) {
		try {
			Consignment consignment = consignmentRepository.getByTxnId(txnId);

			List<StokeDetails> stokrDetails = stokeDetailsRepository.findByTxnIdAndSourceType(txnId, "Importer");

			if(stokrDetails != null) {
				for(StokeDetails detials: stokrDetails) {			

					StockDetailsOperation stockOperation = new StockDetailsOperation();
					stockOperation.setDeviceNumber(detials.getDeviceNumber());
					stockOperation.setDeviceType(detials.getDeviceType());
					stockOperation.setFileName(consignment.getFileName());
					stockOperation.setImei(detials.getImei());
					stockOperation.setFileStatus(consignment.getFileStatus());
					stockOperation.setImeiAction(detials.getImeiAction());
					stockOperation.setOperation("Delete");
					stockOperation.setSourceType("Importer");
					stockOperation.setTxnId(txnId);
					stockOperation.setUserId(consignment.getImporterId());
					stockOperation.setUserName(consignment.getImporterName());
					stockOperation.setCreatedOn(new Date());
					stockOperation.setUpdatedOn(new Date());
					stockOperation.setTaxPaidStatus(consignment.getTaxPaidStatus());

					stockDetailsOperationRepository.save(stockOperation);
				}
			}else {
				StockDetailsOperation stockOperation = new StockDetailsOperation();
				stockOperation.setFileName(consignment.getFileName());
				stockOperation.setFileStatus(consignment.getFileStatus());
				stockOperation.setOperation("Delete");
				stockOperation.setSourceType("Importer");
				stockOperation.setTxnId(txnId);
				stockOperation.setUserId(consignment.getImporterId());
				stockOperation.setUserName(consignment.getImporterName());
				stockOperation.setCreatedOn(new Date());
				stockOperation.setUpdatedOn(new Date());
				stockOperation.setTaxPaidStatus(consignment.getTaxPaidStatus());

				stockDetailsOperationRepository.save(stockOperation);

			}
			stokeDetailsRepository.withdrawStatus("WithdrawnByImporter",txnId);
			consignmentRepository.withdrawStatus("Withdrawn",txnId);


			return new GenricResponse(200, "Delete succssfully");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}



	public List<Consignment> getFilterDetails(FilterRequest filterRequest){
		try {

			return	consignmentRepository.viewUser(filterRequest.getImporterId(), filterRequest.getEndDate(),filterRequest.getStartDate(),
					filterRequest.getTaxPaidStatus(),filterRequest.getFileStatus(),filterRequest.getConsignmentStatus());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());		}
	}




}
