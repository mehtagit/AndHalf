package com.gl.ceir.config.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.Consignment;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
import com.gl.ceir.config.service.impl.DeviceSnapShotServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class ConsignmentController {

	private static final Logger logger = LogManager.getLogger(ConsignmentController.class);

	@Autowired
	ConsignmentServiceImpl consignmentServiceImpl;

	@Autowired
	StolenAndRecoveryServiceImpl stolenAndRecoveryServiceImpl;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StackholderPolicyMappingServiceImpl stackholderPolicyMappingServiceImpl;

	@Autowired
	Utility utility;



	@ApiOperation(value = "Add new consignment with file .", response = GenricResponse.class)
	@RequestMapping(path = "/consignment/upload", method = RequestMethod.POST)

	public GenricResponse uploadFile(@RequestParam("fileName") String fileName, String supplierId,String importerName ,
			String consignmentNumber,String supplierName, Long importerId,int quantity ,String expectedArrivalPort,
			String organisationCountry,String expectedDispatcheDate,String expectedArrivaldate,String filePath) {

		Consignment consignmentUploadRequest = new Consignment();

		consignmentUploadRequest.setSupplierId(supplierId);
		consignmentUploadRequest.setImporterName(importerName);
		consignmentUploadRequest.setConsignmentNumber(consignmentNumber);
		consignmentUploadRequest.setTaxPaidStatus("NotPaid");
		consignmentUploadRequest.setSupplierName(supplierName);
		consignmentUploadRequest.setImporterId(importerId);
		consignmentUploadRequest.setFileStatus("INIT");
		consignmentUploadRequest.setConsignmentStatus("Uploading");
		consignmentUploadRequest.setCreatedOn(new Date());
		consignmentUploadRequest.setModifiedOn(new Date());
		consignmentUploadRequest.setExpectedArrivaldate(expectedArrivaldate);
		consignmentUploadRequest.setExpectedArrivalPort(expectedArrivalPort);
		consignmentUploadRequest.setOrganisationCountry(organisationCountry);
		consignmentUploadRequest.setExpectedDispatcheDate(expectedDispatcheDate);
		consignmentUploadRequest.setFileName(fileName);
		consignmentUploadRequest.setQuantity(quantity);

		logger.info("Upload File Request="+consignmentUploadRequest);

		GenricResponse genricResponse = consignmentServiceImpl.storeFile( filePath,consignmentUploadRequest);

		return genricResponse;
	}


	@ApiOperation(value = "Update Consignment Info.", response = GenricResponse.class)
	@RequestMapping(path = "/consignment/update", method = RequestMethod.POST)
	public GenricResponse updateConsigmentInfo(String  fileName, String supplierId,String importerName ,
			String consignmentNumber,String supplierName, Long importerId,int quantity ,String txnId,String organisationCountry,String expectedDispatcheDate,String expectedArrivaldate
			,String expectedArrivalPort,String path) {

		Consignment consignmentUploadRequest = new Consignment();

		consignmentUploadRequest.setSupplierId(supplierId);
		consignmentUploadRequest.setImporterName(importerName);
		consignmentUploadRequest.setConsignmentNumber(consignmentNumber);
		consignmentUploadRequest.setTaxPaidStatus("Not Paid");
		consignmentUploadRequest.setSupplierName(supplierName);
		consignmentUploadRequest.setImporterId(importerId);
		consignmentUploadRequest.setTxnId(txnId);
		consignmentUploadRequest.setExpectedArrivaldate(expectedArrivaldate);
		consignmentUploadRequest.setExpectedArrivalPort(expectedArrivalPort);
		consignmentUploadRequest.setOrganisationCountry(organisationCountry);
		consignmentUploadRequest.setExpectedDispatcheDate(expectedDispatcheDate);
		consignmentUploadRequest.setQuantity(quantity);

		
		logger.info("Upload File Request="+consignmentUploadRequest.toString());

		logger.info("FIle Path ="+path);
		
		GenricResponse genricResponse =	consignmentServiceImpl.updatestoreFile(fileName,path,consignmentUploadRequest);
		return genricResponse;

	}


	@ApiOperation(value = "View all the list of consignment", response = Consignment.class)
	@RequestMapping(path = "/consignment/details", method = RequestMethod.GET)

	public MappingJacksonValue getByImporterId(@RequestParam("importerId") Long importerId) {

		List<Consignment>  consignment =  consignmentServiceImpl.getAll(importerId);

		MappingJacksonValue mapping = new MappingJacksonValue(consignment);

		return mapping;
	}


	@ApiOperation(value = "View the Particular consignment info.", response = Consignment.class)
	@RequestMapping(path = "/consignment/Record", method = RequestMethod.GET)

	public MappingJacksonValue getByTxnId(@RequestParam("txnId") String txnId) {

		logger.info("Recors Api get request="+txnId);
		Consignment consignmentRecordInfo =consignmentServiceImpl.getRecordInfo(txnId);

		MappingJacksonValue mapping = new MappingJacksonValue(consignmentRecordInfo);

		return mapping;
	}


	@ApiOperation(value = "Download Sample Stoke File.", response = String.class)
	@RequestMapping(path = "/stoke/Download/SampleFile", method = RequestMethod.GET)

	public String downloadSampleFile(String samplFileType) {

		String directoryPath=fileStorageProperties.getDownloadDir();
		if("Stoke".equalsIgnoreCase(samplFileType)) {
			directoryPath= directoryPath+"SampleFiles/StokeSampleFile.csv";
			return directoryPath;
		}else {
			directoryPath= directoryPath+"SampleFiles/StolenAndRecovery.csv";
			return directoryPath;
		}
	}





	@ApiOperation(value = "Download Stoke upload File.", response = String.class)

	@RequestMapping(path = "/stoke/Download/uploadFile", method = RequestMethod.GET)
	public String downloadStrokeFile(String fileName,String txnId,String fileType) {

		String directoryPath=fileStorageProperties.getDownloadDir();

		if("ERROR".equalsIgnoreCase(fileType)) {
			directoryPath = directoryPath+"MyStokeUploads/"+txnId+"/error.csv";
			return directoryPath;
		}else {
			directoryPath = directoryPath+"MyStokeUploads/"+txnId+"/"+fileName;
			return directoryPath;
		}
	}


	@ApiOperation(value = "Delete Consignment.", response = GenricResponse.class)

	@RequestMapping(path = "/consigment/Delete", method = RequestMethod.DELETE)

	public GenricResponse deleteConsigment(String txnId) {

		logger.info("TxnID ="+txnId);

		GenricResponse genricResponse =	consignmentServiceImpl.deleteConsigmentInfo(txnId);

		return genricResponse;

	}

	/*@ApiOperation(value = "View all the filtered list of consignment", response = Consignment.class)
	@RequestMapping(path = "/consignment/filterDetails", method = RequestMethod.POST)

	public ResponseEntity getbyfilter(@RequestBody FilterRequest filterRequest,Pageable pageable) {


	return	consignmentServiceImpl.getFilterDetails(filterRequest);

		
	}*/







}
