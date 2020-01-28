package com.ceir.CeirCode.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.exceptions.ResourceServicesException;
import com.ceir.CeirCode.model.Notification;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.repo.NotificationRepository;
import com.ceir.CeirCode.response.GenricResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ConfigurationManagementServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	NotificationRepository notificationRepository;

	public GenricResponse saveNotification(Notification notification) {
		try {

			notificationRepository.save(notification);
			return new GenricResponse(0, "Notification have been saved Sucessfully", "");

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse saveNotification(String channelType, String message, User userId, Long featureId, String featureName, String subFeature, String featureTxnId,String subject) {
		try {

			notificationRepository.save(new Notification(channelType, message, userId, featureId, featureName, subFeature, featureTxnId,subject));

			return new GenricResponse(0, "Notification have been saved Sucessfully", "");
		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
