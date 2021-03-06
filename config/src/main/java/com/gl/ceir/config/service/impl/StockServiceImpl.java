package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.StockStatus;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.repository.DistributerManagementRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.StockMgmtSpecificationBuiler;

@Service
public class StockServiceImpl {


	private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);


	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	DistributerManagementRepository distributerManagementRepository;


	@Autowired
	StockDetailsOperationRepository stockDetailsOperationRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	public GenricResponse uploadStock(StockMgmt stackholderRequest) {

		try {
			stackholderRequest.setStockStatus(StockStatus.UPLOADING.getCode());

			distributerManagementRepository.save(stackholderRequest);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(stackholderRequest.getTxnId());

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(200,"Upload Successfully",stackholderRequest.getTxnId());

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}






	public List<StockMgmt> getAllData(StockMgmt stockMgmt){
		try {

			return distributerManagementRepository.findByRoleTypeAndUserId(stockMgmt.getInvoiceNumber(), stockMgmt.getUserId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}


	}


	public List<StockMgmt> getAllFilteredData(FilterRequest filterRequest,Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			StockMgmtSpecificationBuiler smsb = new StockMgmtSpecificationBuiler();

			if(Objects.nonNull(filterRequest.getUserId()))
				smsb.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY));

			if(Objects.nonNull(filterRequest.getUserId()))
				smsb.with(new SearchCriteria("roleType", filterRequest.getRoleType(), SearchOperation.EQUALITY));

			if(Objects.nonNull(filterRequest.getConsignmentStatus()))
				smsb.with(new SearchCriteria("stockStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY));


			return distributerManagementRepository.findAll(smsb.build(), pageable).getContent();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}


	}





	public StockMgmt view(StockMgmt stockMgmt) {
		try {

			return distributerManagementRepository.findByRoleTypeAndTxnId(stockMgmt.getRoleType(), stockMgmt.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	@Transactional
	public GenricResponse deleteStockDetailes(StockMgmt stockMgmt) {
		try {

			StockMgmt txnRecord	=	distributerManagementRepository.findByRoleTypeAndTxnId(stockMgmt.getRoleType(), stockMgmt.getTxnId());

			if(txnRecord == null) {

				return new GenricResponse(1000, "No record found against this transactionId.",stockMgmt.getTxnId());
			}else {
				/*
				if(0 == stockMgmt.getStockStatus()||2 == stockMgmt.getStockStatus()) {
				 */
				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(stockMgmt.getTxnId());

				webActionDbRepository.save(webActionDb);

				/*else {

					return new GenricResponse(200, "Operation Not Allowed.",stockMgmt.getTxnId());
				}*/
			}

			return new GenricResponse(0, "Delete In Progress.",stockMgmt.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	@Transactional
	public GenricResponse updateStockInfo(StockMgmt distributerManagement) {

		StockMgmt stackHolderInfo=	distributerManagementRepository.findByRoleTypeAndTxnId(distributerManagement.getRoleType(), distributerManagement.getTxnId());

		if(stackHolderInfo == null) {

			return new GenricResponse(1000, "No record found against this transactionId.",distributerManagement.getTxnId());

		}else {

			if(3 == stackHolderInfo.getStockStatus()) {

				stackHolderInfo.setInvoiceNumber(distributerManagement.getInvoiceNumber());
				stackHolderInfo.setQuantity(distributerManagement.getQuantity());
				stackHolderInfo.setSuplierName(distributerManagement.getSuplierName());
				stackHolderInfo.setSupplierId(distributerManagement.getSupplierId());

				if(distributerManagement.getFileName() != null || distributerManagement.getFileName() != " ") {
					stackHolderInfo.setStockStatus(StockStatus.PROCESSING.getCode());
					stackHolderInfo.setFileName(distributerManagement.getFileName());
				}

				distributerManagementRepository.save(stackHolderInfo);

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(distributerManagement.getTxnId());

				webActionDbRepository.save(webActionDb);

			}else {

				return new GenricResponse(200, "Operation Not Allowed.",distributerManagement.getTxnId());
			}

			return new GenricResponse(200, "Update SuccessFully",distributerManagement.getTxnId());
		}
	}
}







