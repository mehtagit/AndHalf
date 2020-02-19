package com.gl.ceir.config.service.impl;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.PendingTacApprovedDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.PendingTacApprovedRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;

@Service
public class PendingTacApprovedImpl {

	private static final Logger logger = LogManager.getLogger(PendingTacApprovedImpl.class);

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	PendingTacApprovedRepository pendingTacApprovedRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	UserRepository userRepository;

	public GenricResponse saveSystemConfigList(PendingTacApprovedDb pendingTacApprovedDb){
		try {
			if(Objects.isNull(pendingTacApprovedDb.getTac())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}

			pendingTacApprovedRepository.save(pendingTacApprovedDb);
			return new GenricResponse(0);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse findById(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}

			PendingTacApprovedDb pendingTacApprovedDb = pendingTacApprovedRepository.getById(filterRequest.getId());
			return new GenricResponse(0, "SUCCESS", "SUCCESS", pendingTacApprovedDb);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse findByTxnId(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getTxnId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}

			PendingTacApprovedDb pendingTacApprovedDb = pendingTacApprovedRepository.getByTxnId(filterRequest.getTxnId());
			if(Objects.nonNull(pendingTacApprovedDb)) {
				return new GenricResponse(0, "SUCCESS", "SUCCESS", pendingTacApprovedDb);
			}else {
				return new GenricResponse(1, "Not Found", "Not Found", "");
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse deletePendingApproval(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getUserId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			User user = userRepository.getById(filterRequest.getUserId());

			auditTrailRepository.save(new AuditTrail(user.getId(), user.getUsername(), 0L, "System", 0L, 
					Features.CONFIG_LIST, SubFeatures.DELETE, ""));
			logger.info("AUDIT : Delete Tags list saved in audit_trail.");

			if(Objects.nonNull(filterRequest.getTxnId())) {
				pendingTacApprovedRepository.deleteByTxnId(filterRequest.getTxnId());
				return new GenricResponse(0, "Deleted Successully.", "", "");
			}else if(Objects.nonNull(filterRequest.getTac()) && Objects.nonNull(filterRequest.getImporterId())){
				pendingTacApprovedRepository.deleteByTacAndUserId(filterRequest.getTac(), filterRequest.getImporterId());
				return new GenricResponse(0, "Deleted Successully.", "", "");
			}else {
				return new GenricResponse(1, "No Deletion Allowed.", "", "");
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
