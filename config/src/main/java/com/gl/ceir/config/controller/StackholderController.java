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

import com.gl.ceir.config.model.DistributerManagement;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.service.impl.DeviceSnapShotServiceImpl;
import com.gl.ceir.config.service.impl.StackholderServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class StackholderController {


	private static final Logger logger = LogManager.getLogger(DeviceSnapShotServiceImpl.class);

	@Autowired
	StackholderServiceImpl stackholderServiceImpl;



	@ApiOperation(value = "Add Retailer And Distributer Info.", response = GenricResponse.class)
	@RequestMapping(path = "/stackholderStock/upload", method = RequestMethod.POST)

	public GenricResponse addStackHolderInfo( Long  impoterId, Long userId ,@RequestParam("file") MultipartFile file
			, String userName, String invoiceNumber,String moduleType){

		DistributerManagement stackholderRequest = new DistributerManagement();

		stackholderRequest.setImporterId(impoterId);
		stackholderRequest.setUserId(userId);
		stackholderRequest.setFileName(file.getOriginalFilename());
		stackholderRequest.setUserName(userName);
		stackholderRequest.setInvoiceNumber(invoiceNumber);
		stackholderRequest.setModuleType(moduleType);
		stackholderRequest.setCreatedOn(new Date());
		stackholderRequest.setUpdatedOn(new Date());
		stackholderRequest.setFileStatus("INIT");

		GenricResponse genricResponse =	stackholderServiceImpl.saveData(file, stackholderRequest);

		return genricResponse;

	}

	@ApiOperation(value = "Update Retailer And Distributer Info.", response = GenricResponse.class)
	@RequestMapping(path = "/stackholderStock/update", method = RequestMethod.POST)

	public GenricResponse addStackHolderInfo( Long  impoterId, Long userId ,@RequestParam(value ="file", required = false) MultipartFile file
			, String userName, String invoiceNumber,String moduleType,String txnId){

		DistributerManagement stackholderRequest = new DistributerManagement();

		stackholderRequest.setImporterId(impoterId);
		stackholderRequest.setUserId(userId);
		stackholderRequest.setUserName(userName);
		stackholderRequest.setInvoiceNumber(invoiceNumber);
		stackholderRequest.setModuleType(moduleType);
		stackholderRequest.setUpdatedOn(new Date());
		stackholderRequest.setTxnId(txnId);


		GenricResponse genricResponse =	stackholderServiceImpl.updateStockInfo(file, stackholderRequest);

		return genricResponse;

	}



	@ApiOperation(value = "View Retailer And Distributer Info.", response = DistributerManagement.class)
	@RequestMapping(path = "/stackholder/view", method = RequestMethod.GET)

	public MappingJacksonValue findDetalils(String moduleName,Long userId) {

		logger.info("Data Getting request moduleName="+moduleName+"UserId="+userId);

		List<DistributerManagement> response =	stackholderServiceImpl.getAllData(moduleName, userId);

		MappingJacksonValue mapping = new MappingJacksonValue(response);

		return mapping;
	}


	@ApiOperation(value = "View Retailer And Distributer Record of TxnId.", response = DistributerManagement.class)
	@RequestMapping(path = "/stackholder/view/record", method = RequestMethod.GET)

	public MappingJacksonValue findRecordDetalils(String moduleName,String txnId) {

		logger.info("Record Getting info Request moduleName="+moduleName+"TxnId="+txnId);

		DistributerManagement response	= stackholderServiceImpl.getRecord(moduleName, txnId);

		MappingJacksonValue mapping = new MappingJacksonValue(response);

		return mapping;

	}


	@ApiOperation(value = "Delete Retailer And Distributer Record of TxnId.", response = GenricResponse.class)
	@RequestMapping(path = "/stackholder/view/record", method = RequestMethod.DELETE)

	public GenricResponse deleteStachHolderData(String txnId,String moduleName) {

		logger.info("Delete request TxnId="+txnId+"ModuleName="+moduleName);
		GenricResponse genricResponse =	stackholderServiceImpl.deleteStackHolderDetailes(moduleName, txnId);

		return genricResponse;

	}








}
