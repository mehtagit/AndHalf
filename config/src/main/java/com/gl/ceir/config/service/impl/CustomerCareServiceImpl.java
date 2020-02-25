package com.gl.ceir.config.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.factory.CustomerCareFactory;
import com.gl.ceir.config.factory.CustomerCareRepo;
import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.CustomerCareRequest;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.Notification;
import com.gl.ceir.config.model.PolicyBreachNotification;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.repository.PolicyBreachNotificationRepository;
import com.gl.ceir.config.util.Utility;

@Service
public class CustomerCareServiceImpl {

	private static final Logger logger = LogManager.getLogger(CustomerCareServiceImpl.class);

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	UserStaticServiceImpl userStaticServiceImpl;

	@Autowired 
	CustomerCareFactory customerCareFactory;
	
	@Autowired
	PolicyBreachNotificationRepository policyBreachNotificationRepository;

	public GenricResponse getAll(CustomerCareRequest customerCareRequest) {
		try {
			List<CustomerCareDeviceState> customerCareDeviceStates = new LinkedList<>();

			if(Objects.nonNull(customerCareRequest.getImei()) 
					&& "IMEI".equalsIgnoreCase(customerCareRequest.getDeviceIdType())) {

				customerCareFactory.dbsList.stream().forEach( o -> {
					CustomerCareTarget customerCareTarget = customerCareFactory.getObject(o);
					if(Objects.isNull(customerCareTarget)) {
						logger.info("Corresponding object of Db [" + o + "] is not defined in the factory ");
						return;
					}

					// To avoid 500, if only one or few DB's have issue for the imei.
					try {
						customerCareDeviceStates.add(customerCareTarget.fetchDetailsByImei(customerCareRequest.getImei(), new CustomerCareDeviceState()));
					}catch (Exception e) {
						logger.error("Db [" + o + "] have some issue in fetching data for imei [" + customerCareRequest.getImei() + "]", e);
					}
				});

				return new GenricResponse(0, GenericMessageTags.SUCCESS.getMessage(), "", customerCareDeviceStates);
			}else if(Objects.nonNull(customerCareRequest.getMsisdn())){
				// TODO

				return new GenricResponse(0, GenericMessageTags.SUCCESS.getMessage(), "", null);
			}else {
				return new GenricResponse(1, GenericMessageTags.INVALID_REQUEST.getMessage(), "", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	public GenricResponse getByTxnId(CustomerCareDeviceState customerCareDeviceState) {
		try {
			if(Objects.isNull(customerCareDeviceState) ||
					Objects.isNull(customerCareDeviceState.getFeatureId())
					|| Objects.isNull(customerCareDeviceState.getName())) {

				logger.info(GenericMessageTags.NULL_REQ.getMessage());
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), GenericMessageTags.NULL_REQ.getMessage(), "");
			}

			Object objectBytxnId = null;
			CustomerCareRepo repository = null;

			// Getting repository from factory.
			if(customerCareDeviceState.getFeatureId() == 0)
				repository = customerCareFactory.getRepoByName(customerCareDeviceState.getName());
			else
				repository = customerCareFactory.getRepoByFeatureId(customerCareDeviceState.getFeatureId());
			
			// If factory has a valid repo.
			if(Objects.isNull(repository)) {
				logger.info(GenericMessageTags.FEATURE_NOT_SUPPORTED.getMessage() +" txnId [" + customerCareDeviceState.getTxnId() + "]");
				return new GenricResponse(2, GenericMessageTags.FEATURE_NOT_SUPPORTED.getTag(), GenericMessageTags.FEATURE_NOT_SUPPORTED.getMessage(), customerCareDeviceState.getTxnId());
			}

			objectBytxnId = repository.getByTxnId(customerCareDeviceState.getTxnId());

			if(Objects.isNull(objectBytxnId)) {
				logger.info(GenericMessageTags.INVALID_TXN_ID.getMessage() +" txnId [" + customerCareDeviceState.getTxnId() + "]");
				return new GenricResponse(3, GenericMessageTags.INVALID_TXN_ID.getTag(), GenericMessageTags.INVALID_TXN_ID.getMessage(), customerCareDeviceState.getTxnId());
			}else {
				return new GenricResponse(0, GenericMessageTags.SUCCESS.getMessage(), "",  objectBytxnId);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public Page<PolicyBreachNotification> viewPolicyBreachNotification(FilterRequest filterRequest, 
			Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			
			if(Objects.nonNull(filterRequest.getImei())) {
				return policyBreachNotificationRepository.findByImei(filterRequest.getImei(), pageable);
			}
			else if(Objects.nonNull(filterRequest.getContactNumber())){
				return policyBreachNotificationRepository.findByImei(filterRequest.getContactNumber(), pageable);
			}else {
				return new PageImpl<>(new ArrayList<PolicyBreachNotification>(1), pageable, 0L);
			}
			
		} catch (Exception e) {
			logger.error("Not Register Consignent="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
}
