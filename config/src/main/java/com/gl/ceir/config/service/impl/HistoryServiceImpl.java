package com.gl.ceir.config.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.BlacklistDbHistory;
import com.gl.ceir.config.model.ConsignmentMgmtHistoryDb;
import com.gl.ceir.config.model.DeviceDbHistory;
import com.gl.ceir.config.model.GreylistDbHistory;
import com.gl.ceir.config.model.MessageConfigurationHistoryDb;
import com.gl.ceir.config.model.Notification;
import com.gl.ceir.config.model.PolicyConfigurationHistoryDb;
import com.gl.ceir.config.model.StockMgmtHistoryDb;
import com.gl.ceir.config.model.StolenAndRecoveryHistoryMgmt;
import com.gl.ceir.config.model.SystemConfigurationHistoryDb;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.BlackListTrackDetailsRepository;
import com.gl.ceir.config.repository.ConsignmentMgmtHistoryRepository;
import com.gl.ceir.config.repository.GreyListTrackRepository;
import com.gl.ceir.config.repository.MessageConfigurationHistoryDbRepository;
import com.gl.ceir.config.repository.NotificationRepository;
import com.gl.ceir.config.repository.PolicyConfigurationHistoryDbRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StockMgmtHistoryRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryHistoryMgmtRepository;
import com.gl.ceir.config.repository.SystemConfigurationHistoryDbRepository;


@Service
public class HistoryServiceImpl {

	private static final Logger logger = LogManager.getLogger(HistoryServiceImpl.class);



	@Autowired	
	PolicyConfigurationHistoryDbRepository policyConfigurationHistoryDbRepository;

	@Autowired	
	MessageConfigurationHistoryDbRepository messageConfigurationHistoryDbRepository;

	@Autowired
	SystemConfigurationHistoryDbRepository systemConfigurationHistoryDbRepository;

	@Autowired
	BlackListTrackDetailsRepository blackListTrackDetailsRepository;

	@Autowired
	GreyListTrackRepository greyListTrackRepository;

	@Autowired
	StockDetailsOperationRepository stockDetailsOperationRepository;
	
	@Autowired
	StolenAndRecoveryHistoryMgmtRepository stolenAndRecoveryHistoryMgmtRepository;
	
	@Autowired
	ConsignmentMgmtHistoryRepository consignmentMgmtHistoryRepository;

	@Autowired
	StockMgmtHistoryRepository stockMgmtHistoryRepository;
	
	@Autowired
	AuditTrailRepository auditTrailRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	
	public Page<PolicyConfigurationHistoryDb> ViewAllPolicyHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return policyConfigurationHistoryDbRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	public Page<MessageConfigurationHistoryDb> ViewAllMessageHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return messageConfigurationHistoryDbRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	public Page<SystemConfigurationHistoryDb> ViewAllSystemHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return systemConfigurationHistoryDbRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	public Page<BlacklistDbHistory> ViewAllBlackHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return blackListTrackDetailsRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	public Page<GreylistDbHistory> ViewAllGreyHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return greyListTrackRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	public Page<DeviceDbHistory> ViewAllDeviceHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return stockDetailsOperationRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	
	public Page<StolenAndRecoveryHistoryMgmt> ViewAllStolenAndRecoveryHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return stolenAndRecoveryHistoryMgmtRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	
	public Page<ConsignmentMgmtHistoryDb> ViewAllConsignmentHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return consignmentMgmtHistoryRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public Page<StockMgmtHistoryDb> ViewAllStockHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return stockMgmtHistoryRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	
	
	public Page<AuditTrail> ViewAllAuditHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return auditTrailRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	

	

	public Page<Notification> ViewAllNotificationHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return notificationRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
}
