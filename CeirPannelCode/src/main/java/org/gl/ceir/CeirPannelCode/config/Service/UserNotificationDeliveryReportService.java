package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.UserNotificationDeliveryReport;

public interface UserNotificationDeliveryReportService {
	public List<UserNotificationDeliveryReport> getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public UserNotificationDeliveryReport get(String ticketId);
}
