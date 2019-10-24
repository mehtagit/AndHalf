package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.Date;
import java.util.List;

import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.UserNotification;

public interface UserNotificationService {
	public List<UserNotification> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public UserNotification get(String ticketId);
}
