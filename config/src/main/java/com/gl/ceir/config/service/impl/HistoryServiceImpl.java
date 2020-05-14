package com.gl.ceir.config.service.impl;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.BlacklistDbHistory;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GreylistDbHistory;
import com.gl.ceir.config.model.Notification;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StolenAndRecoveryHistoryMgmt;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.BlackListTrackDetailsRepository;
import com.gl.ceir.config.repository.GreyListTrackRepository;
import com.gl.ceir.config.repository.NotificationRepository;
import com.gl.ceir.config.repository.StockMgmtHistoryRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryHistoryMgmtRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.specificationsbuilder.NotificationSpecificationBuilder;

@Service
public class HistoryServiceImpl {

	private static final Logger logger = LogManager.getLogger(HistoryServiceImpl.class);

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;

	@Autowired
	BlackListTrackDetailsRepository blackListTrackDetailsRepository;

	@Autowired
	GreyListTrackRepository greyListTrackRepository;


	@Autowired
	StolenAndRecoveryHistoryMgmtRepository stolenAndRecoveryHistoryMgmtRepository;

	@Autowired
	StockMgmtHistoryRepository stockMgmtHistoryRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	PropertiesReader propertiesReader;

	
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


	


	public Page<StolenAndRecoveryHistoryMgmt> ViewAllStolenAndRecoveryHistory(Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			return stolenAndRecoveryHistoryMgmtRepository.findAll(pageable);
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
			SystemConfigurationDb systemConfigurationDb = systemConfigurationDbRepository.getByTag(ConfigTags.page_size_for_Notification);
			
			int notiPageSize = Integer.parseInt(systemConfigurationDb.getValue());
			Pageable pageable = PageRequest.of(pageNo, notiPageSize);
			return notificationRepository.findAll(pageable);
			
		} catch (NumberFormatException e) {
			logger.error("Integer value is expected for tag [page_size_for_Notification] in system_configuration_db.");
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Page<Notification> ViewAllNotificationHistory(Integer pageNo, Integer pageSize, FilterRequest filterRequest){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
			NotificationSpecificationBuilder nsb = new NotificationSpecificationBuilder(propertiesReader.dialect);

			if("Custom".equalsIgnoreCase(filterRequest.getUserType())) {
				logger.info("skipping userid in where clause for usertype : " + filterRequest.getUserType());
				nsb.with(new SearchCriteria("receiverUserType", "Custom", SearchOperation.EQUALITY, Datatype.STRING));
			}else if("TRC".equalsIgnoreCase(filterRequest.getUserType())){
				logger.info("skipping userid in where clause for usertype : " + filterRequest.getUserType());
				nsb.with(new SearchCriteria("receiverUserType", "TRC", SearchOperation.EQUALITY, Datatype.STRING));
			}else {
				if(Objects.nonNull(filterRequest.getUserId()))
					nsb.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
			}

			nsb.with(new SearchCriteria("referTable", "END_USER", SearchOperation.NEGATION, Datatype.STRING));
			
			return notificationRepository.findAll(nsb.build(), pageable);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
