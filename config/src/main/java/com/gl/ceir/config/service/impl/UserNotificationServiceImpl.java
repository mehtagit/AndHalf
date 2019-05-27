package com.gl.ceir.config.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.UserNotification;
import com.gl.ceir.config.repository.UserNotificationRepositoy;
import com.gl.ceir.config.service.UserNotificationService;

@Service
public class UserNotificationServiceImpl implements UserNotificationService {
	private static final Logger logger = LogManager.getLogger(UserNotificationServiceImpl.class);

	@Autowired
	private UserNotificationRepositoy userNotificationRepositoy;

	@Override
	public List<UserNotification> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		try {
			if (imeiMsisdnIdentity.getMsisdn() == null && imeiMsisdnIdentity.getImei() == null) {
				return null;
			} else if (imeiMsisdnIdentity.getMsisdn() != null && imeiMsisdnIdentity.getImei() != null) {
				return userNotificationRepositoy.findByMsisdnAndImei(imeiMsisdnIdentity.getMsisdn(),
						imeiMsisdnIdentity.getImei());
			} else if (imeiMsisdnIdentity.getMsisdn() != null) {
				return userNotificationRepositoy.findByMsisdn(imeiMsisdnIdentity.getMsisdn());
			} else {
				return userNotificationRepositoy.findByImei(imeiMsisdnIdentity.getImei());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public UserNotification get(String ticketId) {
		try {
			if (ticketId != null) {
				return userNotificationRepositoy.findById(ticketId)
						.orElseThrow(() -> new ResourceNotFoundException("UserNotification ", "ticketId", ticketId));
			}
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
