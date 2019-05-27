package com.gl.ceir.config.service;

import java.util.Date;
import java.util.List;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.UserNotification;

public interface UserNotificationService {
	public List<UserNotification> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public UserNotification get(String ticketId);
}
