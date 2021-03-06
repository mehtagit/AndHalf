package com.gl.ceir.config.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StackholderPolicyMapping;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;
import com.gl.ceir.config.service.impl.DeviceSnapShotServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;
import io.swagger.annotations.ApiOperation;

@RestController
public class StolenAndRecoveryController {

	private static final Logger logger = LogManager.getLogger(DeviceSnapShotServiceImpl.class);

	@Autowired
	StolenAndRecoveryServiceImpl stolenAndRecoveryServiceImpl;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StackholderPolicyMappingServiceImpl stackholderPolicyMappingServiceImpl;

	@Autowired
	Utility utility;


	@ApiOperation(value = "Upload Recovery Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/Recovery", method = RequestMethod.POST)
	public GenricResponse uploadFileAction(@RequestBody StolenandRecoveryMgmt stolenandRecoveryRequest) {

		logger.info("Upload Recovery Details="+stolenandRecoveryRequest);


		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.uploadDetails(stolenandRecoveryRequest);

		logger.info("Upload recovery details response="+genricResponse);

		return genricResponse;

	}


	@ApiOperation(value = "Upload Stolen Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/Stolen", method = RequestMethod.POST)
	public GenricResponse uploadStolenDetails(@RequestBody StolenandRecoveryMgmt stolenandRecoveryDetails)
	{
		logger.info("Stolen upload Request="+stolenandRecoveryDetails);

		if(stolenandRecoveryDetails.getBlockingType() == null || stolenandRecoveryDetails.getBlockingType().equalsIgnoreCase("Default") ||
				stolenandRecoveryDetails.getBlockingType() == "") {

			StackholderPolicyMapping config = stackholderPolicyMappingServiceImpl.getBlackListConfigDetails();
			String newTime = utility.newDate(config.getGraceTimePeriod());

			stolenandRecoveryDetails.setBlockingTimePeriod(newTime);
			stolenandRecoveryDetails.setBlockingType("Default");
		}

		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.uploadDetails(stolenandRecoveryDetails);
		logger.info("Stolen upload Response="+genricResponse);

		return genricResponse;
	}


	@ApiOperation(value = "Upload Multiple Stolen Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/uploadMultiple/Stolen", method = RequestMethod.POST)
	public GenricResponse uploadMultipleStolenDetails(@RequestBody List<StolenandRecoveryMgmt> stolenandRecoveryDetails)
	{
		logger.info("Multiple Stolen Upload Request="+stolenandRecoveryDetails);

		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.uploadMultipleStolen(stolenandRecoveryDetails);

		logger.info("Muliple Stolen Upload Response ="+genricResponse);
		return genricResponse;

	}
	
	@ApiOperation(value = "View Stolen and Recovery Details.", response = StolenandRecoveryMgmt.class)
	@RequestMapping(path = "/stakeholder/record", method = RequestMethod.POST)
	public MappingJacksonValue getAllActionDetails(@RequestBody StolenandRecoveryMgmt stolenandRecoveryDetails) {

		logger.info("Record request to Stolen And Recovery Info="+stolenandRecoveryDetails);

		List<StolenandRecoveryMgmt>	stolenandRecoveryDetailsResponse = stolenAndRecoveryServiceImpl.getAllInfo(stolenandRecoveryDetails);

		MappingJacksonValue mapping = new MappingJacksonValue(stolenandRecoveryDetailsResponse);

		logger.info("Record Response of Stolen And Recovery Info="+mapping);

		return mapping;
	}



	@ApiOperation(value = "Download Stolen And Recovery file.", response = String.class)
	@RequestMapping(value = "/stackholder/download/stolenAndRecoveyfile", method = RequestMethod.GET)
	public String downloadStolenAndRecoveyrFile(@RequestParam("txnId") String txnId,@RequestParam("fileName") String fileName,
			@RequestParam("fileType") String fileType) {

		String serverPath=fileStorageProperties.getActionUploadDir();

		if(fileType.equals("ERROR")) {
			serverPath= serverPath+"action/"+txnId+"/error.csv";
			return serverPath;
		}else {
			serverPath= serverPath+"action/"+txnId+"/"+fileName;
			return serverPath;
		}
	}



}
