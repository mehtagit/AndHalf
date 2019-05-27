package com.gl.ceir.config.service;

import java.util.List;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.UserNotificationDeliveryReport;

public interface UserNotificationDeliveryReportService {
	public List<UserNotificationDeliveryReport> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public UserNotificationDeliveryReport get(String ticketId);
}
