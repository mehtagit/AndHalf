package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
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



	@ApiOperation(value = "Add new consignment.", response = GenricResponse.class)

	@RequestMapping(path = "/consignment/register", method = RequestMethod.POST)

	public GenricResponse uploadFile(@RequestBody ConsignmentMgmt consignmentUploadRequest) {

		logger.info("Consignment Register Request="+consignmentUploadRequest);

		GenricResponse genricResponse = consignmentServiceImpl.registerConsignment( consignmentUploadRequest);

		logger.info("Consignment Register Response="+genricResponse);

		return genricResponse;
	}


	@ApiOperation(value = "Update Consignment Info.", response = GenricResponse.class)
	@RequestMapping(path = "/consignment/update", method = RequestMethod.POST)
	public GenricResponse updateConsigmentInfo(@RequestBody ConsignmentMgmt consignmentUploadRequest) {

		logger.info("Update Consignment Request="+consignmentUploadRequest.toString());

		GenricResponse genricResponse =	consignmentServiceImpl.updateConsignment(consignmentUploadRequest);

		logger.info("Update Consignment Response ="+genricResponse);
		return genricResponse;

	}


	@ApiOperation(value = "View all the list of consignment", response = ConsignmentMgmt.class)
	@RequestMapping(path = "/consignment/Record", method = RequestMethod.GET)

	public MappingJacksonValue getByImporterId(@RequestParam("userId") Long userId) {

		logger.info("Request TO view TO all record of user="+userId);

		List<ConsignmentMgmt>  consignment =  consignmentServiceImpl.getAll(userId);

		MappingJacksonValue mapping = new MappingJacksonValue(consignment);

		logger.info("Response of view Request ="+mapping);

		return mapping;
	}
	
	@ApiOperation(value = "View filtered consignment", response = ConsignmentMgmt.class)
	@PostMapping("/filter/consignment")
	public MappingJacksonValue filterConsignments(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		logger.info("Request TO view filtered consignment = " + filterRequest);
		
		Page<ConsignmentMgmt>  consignment =  consignmentServiceImpl.getFilterConsignments(filterRequest, pageNo, pageSize);

		MappingJacksonValue mapping = new MappingJacksonValue(consignment);

		logger.info("Response of view Request ="+mapping);

		return mapping;
	}



	@ApiOperation(value = "View the Particular consignment info.", response = ConsignmentMgmt.class)
	@RequestMapping(path = "/consignment/view", method = RequestMethod.GET)

	public MappingJacksonValue getByTxnId(@RequestParam("txnId") String txnId) {

		logger.info("View Request only Single Record="+txnId);
		ConsignmentMgmt consignmentRecordInfo =consignmentServiceImpl.getRecordInfo(txnId);

		MappingJacksonValue mapping = new MappingJacksonValue(consignmentRecordInfo);

		logger.info("Response of View ="+mapping);
		return mapping;
	}


	@ApiOperation(value = "Download Sample Stoke File.", response = String.class)
	@RequestMapping(path = "/Download/SampleFile", method = RequestMethod.GET)

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

	@RequestMapping(path = "/Download/uploadFile", method = RequestMethod.GET)
	public String downloadStrokeFile(String fileName,String txnId,String fileType) {

		String directoryPath=fileStorageProperties.getDownloadDir();

		if("ERROR".equalsIgnoreCase(fileType)) {
			directoryPath ="http://13.233.39.58:9090/CEIR/Design/"+txnId+"/error.csv";
			return directoryPath;
		}else {	
			directoryPath = "http://13.233.39.58:9090/CEIR/Design/"+txnId+"/"+fileName;
			return directoryPath;
		}
	}


	@ApiOperation(value = "Delete Consignment.", response = GenricResponse.class)

	@RequestMapping(path = "/consigment/delete", method = RequestMethod.DELETE)

	public GenricResponse deleteConsigment(@RequestBody ConsignmentMgmt consignmentUploadRequest) {

		logger.info("Consignment Withdraw Request ="+consignmentUploadRequest);

		GenricResponse genricResponse =	consignmentServiceImpl.deleteConsigmentInfo(consignmentUploadRequest);

		logger.info("Response of Delete Request="+genricResponse);
		
		return genricResponse;

	}

	
	
	


}
