package com.gl.ceir.config.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StackholderPolicyMapping;
import com.gl.ceir.config.model.StolenandRecoveryDetails;
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

	@RequestMapping(path = "/stackholder/Recovery", method = RequestMethod.POST)

	public GenricResponse uploadFileAction(@RequestParam("file") MultipartFile file, Long userId,String sourceType ) {

		logger.info("User id="+userId+"SourceType="+sourceType);

		StolenandRecoveryDetails stolenandRecoveryDetails = new StolenandRecoveryDetails();
		stolenandRecoveryDetails.setUserId(userId);
		stolenandRecoveryDetails.setSourceType(sourceType);
		stolenandRecoveryDetails.setCreatedOn(new Date());
		stolenandRecoveryDetails.setUpdatedOn(new Date());
		stolenandRecoveryDetails.setFileModeType("Recovery");
		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.storeFile(file, stolenandRecoveryDetails);

		return genricResponse;

	}


	@ApiOperation(value = "Upload Stolen Details.", response = GenricResponse.class)

	@RequestMapping(path = "/stackholder/Stolen", method = RequestMethod.POST)

	public GenricResponse uploadStolenDetails(@RequestParam("file") MultipartFile file, Long userId,String sourceType,String blockingType,String blockTimePeriod)
	{
		StolenandRecoveryDetails stolenandRecoveryDetails = new StolenandRecoveryDetails();
		stolenandRecoveryDetails.setUserId(userId);
		stolenandRecoveryDetails.setSourceType(sourceType);
		stolenandRecoveryDetails.setCreatedOn(new Date());
		stolenandRecoveryDetails.setUpdatedOn(new Date());
		stolenandRecoveryDetails.setFileModeType("Stolen");
		stolenandRecoveryDetails.setFileStatus("INIT");

		if(blockingType == null || blockingType.equalsIgnoreCase("Default") || blockingType == "") {

			StackholderPolicyMapping config = stackholderPolicyMappingServiceImpl.getBlackListConfigDetails();
			String newTime = utility.newDate(config.getGraceTimePeriod());

			stolenandRecoveryDetails.setBlockingTimePeriod(newTime);
			stolenandRecoveryDetails.setBlockingType("Default");

		}
		else {
			stolenandRecoveryDetails.setBlockingTimePeriod(blockTimePeriod);
			stolenandRecoveryDetails.setBlockingType(blockingType);
		}

		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.storeFile(file, stolenandRecoveryDetails);
		return genricResponse;
	}


	@ApiOperation(value = "View Stolen and Recovery Details.", response = StolenandRecoveryDetails.class)

	@RequestMapping(path = "/stackholder/ActionView", method = RequestMethod.GET)

	public MappingJacksonValue getActionAllDetails(Long userId,String sourceType) {

		logger.info("Stolen And Recovery Info userId="+userId+"SourceType="+sourceType);

		List<StolenandRecoveryDetails>	stolenandRecoveryDetails = stolenAndRecoveryServiceImpl.getAllInfo(userId, sourceType);

		MappingJacksonValue mapping = new MappingJacksonValue(stolenandRecoveryDetails);

		return mapping;
	}



	@ApiOperation(value = "Download Stolen And Recovery file.", response = String.class)
	@RequestMapping(value = "/stackholder/download/stolenAndRecoveyfile", method = RequestMethod.GET)

	public String downloadStolenAndRecoveyrFile(@RequestParam("txnId") String txnId,@RequestParam("fileName") String fileName,@RequestParam("fileType") String fileType) {

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
