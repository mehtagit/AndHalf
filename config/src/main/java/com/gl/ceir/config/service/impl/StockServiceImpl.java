package com.gl.ceir.config.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.repository.DistributerManagementRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

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

			distributerManagementRepository.save(stackholderRequest);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature("Stock");
			webActionDb.setSubFeature("Upload");
			webActionDb.setState(0);
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

				if(3 == stockMgmt.getStockStatus()) {

					WebActionDb webActionDb = new WebActionDb();
					webActionDb.setFeature("Stock");
					webActionDb.setSubFeature("Delete");
					webActionDb.setState(0);
					webActionDb.setTxnId(stockMgmt.getTxnId());

					webActionDbRepository.save(webActionDb);

				}else {

					return new GenricResponse(200, "Operation Not Allowed.",stockMgmt.getTxnId());
				}
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
					stackHolderInfo.setStockStatus(1);
					stackHolderInfo.setFileName(distributerManagement.getFileName());
				}

				distributerManagementRepository.save(stackHolderInfo);

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature("Stock");
				webActionDb.setSubFeature("Update");
				webActionDb.setState(0);
				webActionDb.setTxnId(distributerManagement.getTxnId());

				webActionDbRepository.save(webActionDb);

			}else {

				return new GenricResponse(200, "Operation Not Allowed.",distributerManagement.getTxnId());
			}

			return new GenricResponse(200, "Update SuccessFully",distributerManagement.getTxnId());
		}
	}
}







