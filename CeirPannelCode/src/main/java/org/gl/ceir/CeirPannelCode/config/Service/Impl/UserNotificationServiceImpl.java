package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.UserNotification;
import org.gl.ceir.CeirPannelCode.config.Repository.UserNotificationRepositoy;
import org.gl.ceir.CeirPannelCode.config.Service.UserNotificationService;

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

		} catch (ResourceNotFoundException e) {
			throw e;
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
		} catch (ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
