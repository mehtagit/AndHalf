package com.gl.ceir.config.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.DistributerManagement;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StockDetailsOperation;
import com.gl.ceir.config.model.StokeDetails;
import com.gl.ceir.config.repository.DistributerManagementRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;

@Service
public class StackholderServiceImpl {


	private static final Logger logger = LogManager.getLogger(StackholderServiceImpl.class);

	private final static String NUMERIC_STRING = "0123456789";

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	DistributerManagementRepository distributerManagementRepository;


	@Autowired
	StockDetailsOperationRepository stockDetailsOperationRepository;

	public GenricResponse saveData(String  filePath ,DistributerManagement stackholderRequest) {

		try {
			String txnId=getTxnId();

			String serverPath=fileStorageProperties.getStokeUploadDir();
			serverPath = serverPath.replace("txnId", txnId);

			File dir = new File(serverPath);

			if (!dir.exists()) {
				boolean status=	dir.mkdirs();
			}

			Path temp = Files.move(Paths.get(filePath),Paths.get(serverPath+"/"+stackholderRequest.getFileName()));

			stackholderRequest.setTxnId(txnId);
			distributerManagementRepository.save(stackholderRequest);

			return new GenricResponse(200,"Upload Successfully");

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
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




	public List<DistributerManagement> getAllData(String moduleType,Long userId){
		try {

			return distributerManagementRepository.findByModuleTypeAndUserId(moduleType, userId);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}


	}


	public DistributerManagement getRecord(String moduleType,String txnId) {
		try {

			return distributerManagementRepository.findByModuleTypeAndTxnId(moduleType, txnId);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	public GenricResponse deleteStackHolderDetailes(String moduleName,String txnId) {
		try {

			DistributerManagement txnRecord	=	distributerManagementRepository.findByModuleTypeAndTxnId(moduleName, txnId);

			List<StokeDetails> stokeDetails =   stokeDetailsRepository.findByTxnIdAndSourceType(txnId,moduleName);

			distributerManagementRepository.deleteByTxnId(txnId);

			stokeDetailsRepository.deleteByTxnId(txnId);

			for(StokeDetails detials: stokeDetails) {			

				StockDetailsOperation stockOperation = new StockDetailsOperation();
				stockOperation.setDeviceNumber(detials.getDeviceNumber());
				stockOperation.setDeviceType(detials.getDeviceType());
				stockOperation.setFileName(txnRecord.getFileName());
				stockOperation.setImei(detials.getImei());
				stockOperation.setFileStatus(txnRecord.getFileStatus());
				stockOperation.setImeiAction(detials.getImeiAction());
				stockOperation.setOperation("Delete");
				stockOperation.setSourceType(moduleName);
				stockOperation.setTxnId(txnId);
				stockOperation.setUserId(txnRecord.getImporterId());
				stockOperation.setUserName(txnRecord.getUserName());
				stockOperation.setCreatedOn(new Date());
				stockOperation.setUpdatedOn(new Date());
				stockOperation.setImporterId(txnRecord.getImporterId());
				stockOperation.setInvoiceNumber(stockOperation.getInvoiceNumber());

				stockDetailsOperationRepository.save(stockOperation);
			}


			return new GenricResponse(200, "Delete Successfully.");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse updateStockInfo(String filePath,DistributerManagement distributerManagement) {

		DistributerManagement stackHolderInfo=	distributerManagementRepository.findByModuleTypeAndTxnId(distributerManagement.getModuleType(), distributerManagement.getTxnId());

		if(stackHolderInfo == null) {

			return new GenricResponse(1000, "No record found against this transactionId.");

		}else {
			if(filePath == null || filePath.isEmpty()) {

				distributerManagementRepository.updateUser(distributerManagement.getImporterId(), distributerManagement.getUserName(), distributerManagement.getInvoiceNumber(), new Date(), distributerManagement.getQuantity(),distributerManagement.getTxnId());

				return new GenricResponse(200, "Update SuccessFully");

			}else {

				distributerManagementRepository.updateUserFileStatus(distributerManagement.getImporterId(), distributerManagement.getUserName(), distributerManagement.getInvoiceNumber(), new Date(), distributerManagement.getQuantity(),"INIT",distributerManagement.getFileName(),distributerManagement.getTxnId());

				List<StokeDetails> stokeDetails =stokeDetailsRepository.findByTxnIdAndSourceType(distributerManagement.getTxnId(),distributerManagement.getModuleType());

				if(stokeDetails != null) {
					for(StokeDetails detials: stokeDetails) {			

						StockDetailsOperation stockOperation = new StockDetailsOperation();
						stockOperation.setDeviceNumber(detials.getDeviceNumber());
						stockOperation.setDeviceType(detials.getDeviceType());
						stockOperation.setFileName(stackHolderInfo.getFileName());
						stockOperation.setImei(detials.getImei());
						stockOperation.setFileStatus(stackHolderInfo.getFileStatus());
						stockOperation.setImeiAction(detials.getImeiAction());
						stockOperation.setOperation("Update");
						stockOperation.setSourceType(distributerManagement.getModuleType());
						stockOperation.setTxnId(distributerManagement.getTxnId());
						stockOperation.setUserId(stackHolderInfo.getImporterId());
						stockOperation.setUserName(stackHolderInfo.getUserName());
						stockOperation.setCreatedOn(new Date());
						stockOperation.setUpdatedOn(new Date());
						stockOperation.setImporterId(stackHolderInfo.getImporterId());
						stockOperation.setInvoiceNumber(stockOperation.getInvoiceNumber());

						stockDetailsOperationRepository.save(stockOperation);
					}
				}
				stokeDetailsRepository.deleteByTxnId(distributerManagement.getTxnId());

				return new GenricResponse(200, "Update SuccessFully");
			}
		}
	}






}
