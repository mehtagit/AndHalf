package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.UserNotificationDeliveryReport;
import org.gl.ceir.CeirPannelCode.config.Repository.UserNotificationDeliveryReportRepositoy;
import org.gl.ceir.CeirPannelCode.config.Service.UserNotificationDeliveryReportService;

@Service
public class UserNotificationDeliveryReportServiceImpl implements UserNotificationDeliveryReportService {

	private static final Logger logger = LogManager.getLogger(UserNotificationDeliveryReportServiceImpl.class);

	@Autowired
	private UserNotificationDeliveryReportRepositoy UserNotificationDeliveryReportRepositoy;

	@Override
	public List<UserNotificationDeliveryReport> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		try {
			if (imeiMsisdnIdentity.getMsisdn() == null && imeiMsisdnIdentity.getImei() == null) {
				return null;
			} else if (imeiMsisdnIdentity.getMsisdn() != null && imeiMsisdnIdentity.getImei() != null) {
				return UserNotificationDeliveryReportRepositoy.findByMsisdnAndImei(imeiMsisdnIdentity.getMsisdn(),
						imeiMsisdnIdentity.getImei());
			} else if (imeiMsisdnIdentity.getMsisdn() != null) {
				return UserNotificationDeliveryReportRepositoy.findByMsisdn(imeiMsisdnIdentity.getMsisdn());
			} else {
				return UserNotificationDeliveryReportRepositoy.findByImei(imeiMsisdnIdentity.getImei());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Override
	public UserNotificationDeliveryReport get(String ticketId) {
		try {
			if (ticketId != null) {
				return UserNotificationDeliveryReportRepositoy.findById(ticketId)
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
