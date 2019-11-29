package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RequestCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.StockStatus;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.UserRepository;
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
	StockManagementRepository stockManagementRepository;


	@Autowired
	StockDetailsOperationRepository stockDetailsOperationRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	UserRepository userRepository;

	public GenricResponse uploadStock(StockMgmt stackholderRequest) {

		try {
			stackholderRequest.setStockStatus(StockStatus.UPLOADING.getCode());

			if("Custom".equalsIgnoreCase(stackholderRequest.getUserType())) {
				User user =	userRepository.getByUsername(stackholderRequest.getSupplierId());
				stackholderRequest.setUserId(new Long(user.getId()));
			}

			stockManagementRepository.save(stackholderRequest);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(stackholderRequest.getTxnId());

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(0,"Upload Successfully",stackholderRequest.getTxnId());

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<StockMgmt> getAllData(StockMgmt stockMgmt){
		try {

			return stockManagementRepository.findByRoleTypeAndUserId(stockMgmt.getInvoiceNumber(), stockMgmt.getUserId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Page<StockMgmt> getAllFilteredData(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			if("CEIRAdmin".equalsIgnoreCase(filterRequest.getUserType())) {
				filterRequest.setUserType("Custom");
			}
			
			StockMgmtSpecificationBuiler smsb = new StockMgmtSpecificationBuiler();

			if("Importer".equalsIgnoreCase(filterRequest.getUserType()) || "Distributor".equalsIgnoreCase(filterRequest.getUserType())) {
				if(Objects.nonNull(filterRequest.getUserId()) )
					smsb.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
				
				if(Objects.nonNull(filterRequest.getUserId()))
					smsb.with(new SearchCriteria("roleType", filterRequest.getRoleType(), SearchOperation.EQUALITY, Datatype.STRING));

			} 

			if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
				smsb.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
				smsb.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getTxnId()))
				smsb.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getUserType()) && "Custom".equalsIgnoreCase(filterRequest.getUserType()))
				smsb.with(new SearchCriteria("userType", filterRequest.getUserType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
				smsb.with(new SearchCriteria("stockStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			}

			return stockManagementRepository.findAll(smsb.build(), pageable);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public StockMgmt view(StockMgmt stockMgmt) {
		try {

			return stockManagementRepository.findByRoleTypeAndTxnId(stockMgmt.getRoleType(), stockMgmt.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse deleteStockDetailes(StockMgmt stockMgmt) {
		try {

			StockMgmt txnRecord	=	stockManagementRepository.findByRoleTypeAndTxnId(stockMgmt.getRoleType(), stockMgmt.getTxnId());

			if(Objects.isNull(txnRecord)) {
				return new GenricResponse(1000, "No record found against this transactionId.",stockMgmt.getTxnId());
			}else {

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(stockMgmt.getTxnId());

				webActionDbRepository.save(webActionDb);
			}

			return new GenricResponse(0, "Delete In Progress.",stockMgmt.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse updateStockInfo(StockMgmt distributerManagement) {

		StockMgmt stackHolderInfo=	stockManagementRepository.findByRoleTypeAndTxnId(distributerManagement.getRoleType(), distributerManagement.getTxnId());

		if(Objects.isNull(stackHolderInfo)) {
			return new GenricResponse(1000, "No record found against this transactionId.",distributerManagement.getTxnId());

		}else {

			stackHolderInfo.setInvoiceNumber(distributerManagement.getInvoiceNumber());
			stackHolderInfo.setQuantity(distributerManagement.getQuantity());
			stackHolderInfo.setSuplierName(distributerManagement.getSuplierName());
			stackHolderInfo.setSupplierId(distributerManagement.getSupplierId());
			stackHolderInfo.setTotalPrice(distributerManagement.getTotalPrice());

			if(distributerManagement.getFileName() != null || distributerManagement.getFileName() != " ") {
				stackHolderInfo.setStockStatus(StockStatus.PROCESSING.getCode());
				stackHolderInfo.setFileName(distributerManagement.getFileName());
			}

			stockManagementRepository.save(stackHolderInfo);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(distributerManagement.getTxnId());

			webActionDbRepository.save(webActionDb);

			/*}else {

				return new GenricResponse(200, "Operation Not Allowed.",distributerManagement.getTxnId());
			}*/

			return new GenricResponse(0, "Update SuccessFully",distributerManagement.getTxnId());
		}
	}

	public RequestCountAndQuantity getStockCountAndQuantity( long userId, Integer stockStatus) {
		try {
			logger.info("Going to get  stock count and quantity.");
			return stockManagementRepository.getStockCountAndQuantity(userId, stockStatus);
		} catch (Exception e) {
			// logger.error(e.getMessage(), e);
			// throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
			
			return new RequestCountAndQuantity(0,0);
		}

	}	
}